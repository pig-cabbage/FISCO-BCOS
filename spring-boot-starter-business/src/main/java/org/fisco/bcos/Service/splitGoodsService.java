package org.fisco.bcos.Service;


import org.fisco.bcos.dao.GoodsMapper;
import org.fisco.bcos.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang.math.NumberUtils.min;


@Service
public class splitGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    public float getSimilarityRatio(String str,String target){
        return 1 - (float) compare(str, target) / Math.max(str.length(), target.length());
    }
    public int compare(String str,String target){
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }

                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    public List<Goods> splitByPrice(Integer begin, Integer end){

        System.out.println(begin);
        return goodsMapper.selectByScorePrice(begin,end);
    }

    public List<Goods> splitByName(String name){
        List<Goods> allList=goodsMapper.selectAll();
        Map<Goods,Float> scoreList=new HashMap<>();
        for(Integer i=0;i<allList.size();i++){
            scoreList.put(allList.get(i),getSimilarityRatio(allList.get(i).getGoodsName(),name));
        }
        List<Map.Entry<Goods,Float>> list_data=new ArrayList<Map.Entry<Goods, Float>>(scoreList.entrySet());
        Collections.sort(list_data, new Comparator<Map.Entry<Goods, Float>>() {
                    @Override
                    public int compare(Map.Entry<Goods, Float> o1, Map.Entry<Goods, Float> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
        List<Goods> result=new ArrayList<>();
        for(Integer i=0;i<list_data.size();i++){
            result.add(list_data.get(i).getKey());
        }
        return result;

    }

}