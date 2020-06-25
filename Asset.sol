pragma solidity ^0.4.24;

import "./Table.sol";

contract Asset{
    //event
    event UserRegisterEvent(int256 id, int256 score);
    event BussinessRegisterEvent(int256 id, int256 score);
    event TransferEvent(int256 ret, string from_account, string to_account, uint256 amount);

    string public _key1="user";
    string public _key2="bussiness";
	
	//权限控制
	modifier onlyUser(){
        require(msg.sender==0x5b53f124d15db5d770da16b76d280fe4421dda7c,"You are not User");
        _;
    }
	modifier onlyBussiness(){
        require(msg.sender==0xa7193b772501066c8a809d8903ce2e0af5d7a9e4,"You are not teacher");
        _;
    }
	modifier onlyBank(){
        require(msg.sender==0x7b52ba0c6f7ec03dec45d60721853ee38775da6e,"You are not teacher");
        _;
    }
    
    constructor() public {
        // 构造函数中创建t_asset表
        createTable();
    }
    
    function createTable() private {
        TableFactory tf = TableFactory(0x1001);
        // 用户积分管理表t_user, key : key="user", field:id,score;
        // 企业积分管理表 t_bussiness,key="bussiness", ,filed: id,score;
        // 创建表
        tf.createTable("t_user","key","id,score");
        tf.createTable("t_bussiness","key","id,score");
    }
    function openUserTable() private returns(Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_user");
        return table;
    }
    
    function openBussinessTable() private returns(Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_bussiness");
        return table;
    }
    
    
    /*
        userRegister
        --------------------
        input:id
        output:1, successful
               0, unsuccessful
    
    */
    function userRegister(int256 id) public onlyUser returns(int256)  {
         // Open the Bussiness table
        Table table=openUserTable();
        // search
        Condition con=table.newCondition();
        string memory key="user";
        con.EQ("key",key);
        con.EQ("id",id);
        Entries entries=table.select(key,con);
        int256 zero=0;
        if(0==uint(entries.size())){
            Entry user=table.newEntry();
            user.set("key",key);
            user.set("id",id);
            user.set("score",zero);
            int count =table.insert(key,user);
            emit UserRegisterEvent(id,0);
            return 1;
        }else{
            return 0;
        }
    }
    
    /*
        bussinessRegister
        --------------------
        input:id
        output:1, successful
               0, unsuccessful
    
    */
    function bussinessRegister(int256 id) public onlyBussiness returns(int256){
         // Open the Bussiness table
        Table table=openBussinessTable();
        // search
         Condition con=table.newCondition();
        string memory key="bussiness";
        con.EQ("key",key);
        con.EQ("id",id);
        Entries entries=table.select(key,con);
        int256 zero=0;
        if(0==uint(entries.size())){
            table=openBussinessTable();
            Entry entry=table.newEntry();
            entry.set("key",key);
            entry.set("id",id);
            entry.set("score",zero);
            int count =table.insert(key,entry);
            emit BussinessRegisterEvent(id,0);
            return 1;
        }else{
            return 0;
        }
    }
    
    
    
    function searchAllBussinessScore()public constant onlyBank returns(bytes32[],int[]){
        Table table=openBussinessTable();
        Entries entries=table.select(_key2,table.newCondition());
        
        bytes32[] memory id_list=new bytes32[](uint256(entries.size()));
        int[] memory score_list=new int[](uint256(entries.size()));
        for(int i=0;i<entries.size();i++){
            Entry entry=entries.get(i);
            
            id_list[uint256(i)]=entry.getBytes32("id");
            score_list[uint256(i)]=entry.getInt("score");
            
        }

        return (id_list,score_list);
    }
    
    
    
    
    
    
    function searchBussinessScore(int256 id)public constant onlyBussiness returns(int256){
        // Open the Bussiness table
        Table table=openBussinessTable();
        // search
        Condition con=table.newCondition();
        con.EQ("key",_key2);
        con.EQ("id",id);
        Entries entries=table.select(_key2,con);
        int256 score=0;
        if(0==uint(entries.size())){
            bussinessRegister(id);
            return 0;
        }else{
            Entry entry=entries.get(0);
            return int256(entry.getInt("score"));
        }
    }
    
    function searchUserScore(int256 id) public onlyUser returns(int256){
        // Open the Bussiness table
        Table table=openUserTable();
        // search
        Condition con=table.newCondition();
        string memory key="user";
        con.EQ("key",key);
        con.EQ("id",id);
        Entries entries=table.select(key,con);
        int256 score=0;
        if(0==uint(entries.size())){
            userRegister(id);
            return 0;
        }else{
            Entry entry=entries.get(0);
            return int256(entry.getInt("score"));
        }
    } 
    
    /*
        bussiness apply score
    */
    function applyScore(int256 id,int256  score)public onlyBank returns(int256){
         // Open the Bussiness table
        Table table=openBussinessTable();
        // search
        Condition con=table.newCondition();
        con.EQ("key",_key2);
        con.EQ("id",id);
        Entries entries=table.select(_key2,con);
        if(0==uint(entries.size())){
            return 0;
        }else{
            Entry entry=entries.get(0);
            int256 newScore=int256(entry.getInt("score"))+score;
            updateBussiness(id,newScore);
            return 1;
        }
    }
    
    
    /*
        uesr transfer score to another user
    */
    function userTransfer(int256 id_01,int256 id_02,int256 score)public onlyUser returns(int256){
        Table table=openUserTable();
        //search
        
        Condition con1=table.newCondition();
        con1.EQ("key",_key1);
        con1.EQ("id",id_01);
        Condition con2=table.newCondition();
        con2.EQ("key",_key1);
        con2.EQ("id",id_02);
        
        Entries ens1=table.select(_key1,con1);
        Entries ens2=table.select(_key1,con2);
        int256 score1=0;
        int256 score2=0;
        if(0==uint(ens1.size()) || 0==uint(ens2.size())){
            return 0;
        }else{
            Entry e1=ens1.get(0);
            Entry e2=ens2.get(0);
            score1=int256(e1.getInt("score"));
            score2=int256(e2.getInt("score"));
            if(score1-score<0){
                return 0;
            }else{
                score1=score1-score;
                score2=score2+score;
                updateUser(id_01,score1);
                updateUser(id_02,score2);
                return 1;
            }
        }
    } 
    
    /*
        user buy score
    */
    function buyScore(int256 user_id, int256 bussiness_id,int256 score)public onlyUser returns(int256){
        
        //open table
        Table t1=openUserTable();
        Table t2=openBussinessTable();
        //search
        // string memory key1="user";
        Condition con1=t1.newCondition();
        con1.EQ("key",_key1);
        con1.EQ("id",user_id);
        Entries ens1=t1.select(_key1,con1);
        
        // string memory key2="bussiness";
        Condition con2=t2.newCondition();
        con2.EQ("key",_key2);
        con2.EQ("id",bussiness_id);
        Entries ens2=t2.select(_key2,con2);
        int256 score1=0;
        int256 score2=0;
        if(0==uint(ens1.size()) || 0==uint(ens2.size())){
            return 0;
        }else{
            Entry e1=ens1.get(0);
            Entry e2=ens2.get(0);
            score1=int256(e1.getInt("score"));
            score2=int256(e2.getInt("score"));
            if(score2-score<0){
                return 0;
            }else{
                score1=score1+score;
                score2=score2-score;
                updateUser(user_id,score1);
                updateBussiness(bussiness_id,score2);
                return 1;
            }
        }
    }
    
    /*
        update one user's information 
    */
    function updateUser(int256 id,int256 score)private{
        Table table=openUserTable();
        Entry entry=table.newEntry();
        entry.set("key",_key1);
        entry.set("id",id);
        entry.set("score",score);
        Condition con=table.newCondition();
        con.EQ("key",_key1);
        con.EQ("id",id);
        table.update(_key1,entry,con);
        
    }
    
    /*
        update one bussiness's information
    */
    function updateBussiness(int256 id,int256 score)private{
        Table table=openBussinessTable();
        Entry entry=table.newEntry();
        entry.set("id",id);
        entry.set("score",score);
        Condition con=table.newCondition();
        con.EQ("key",_key2);
        con.EQ("id",id);
        
        table.update(_key2,entry,con);
    }
    
    
    
    
    
    
}