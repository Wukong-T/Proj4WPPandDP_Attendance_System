<template>
  <body> 
    <div><el-card>

      <div style="text-align:left">
        <!-- 查询条件输入部分 -->
        <el-form :inline="true" :model="searchForm" class="demo-form-inline">

          <el-form-item
            label-width="50px"
            label-position="labelPosition"
          >
            <el-input
              id="fname"
              clearable
              style="width: 150px"
              v-model="searchForm.realname"
              placeholder="输入查询的姓名"
            ></el-input>
          </el-form-item>

          <el-form-item>
            <el-button round icon="el-icon-search" @click="search('ruleForm')"
              >查询</el-button
            >

 
          </el-form-item>

          <el-form-item  label="按用户类型显示" prop="choice" >
                  <el-select 
                    v-model="searchForm.choice" 
                    placeholder="按用户类型显示"
                    @change="showByChoice()"
                    prop="choice">
                    <el-option label="全部" value="全部"></el-option>
                    <el-option label="员工" value="员工"></el-option>
                    <el-option label="部门经理" value="部门经理"></el-option>
                    <el-option label="副经理" value="副经理"></el-option>
                     <el-option label="总经理" value="总经理"></el-option>
                  </el-select>

                  <el-button size="small" style="margin-left: 40px;"  type="danger" 
                   @click="delAll()" >删除全部用户信息</el-button>
                </el-form-item>

        </el-form>
      </div>

    <el-table class="mytable" :data="tableData" border style="width: 100%">
      <!-- <el-table-column type="index" label="序号"  width="50px" ></el-table-column> -->
      <el-table-column prop="username" align="center" label="账号"  width="100px"></el-table-column>
      <el-table-column prop="realname" align="center" label="真实姓名"  width="100px"></el-table-column>
      <el-table-column prop="email"   align="center" label="邮箱"  width="200px"></el-table-column>
      <el-table-column prop="choice" align="center"  width="100"  label="用户类型"></el-table-column>
      
      <el-table-column label="操作">

        <template slot-scope="scope">
          <el-button
            icon="el-icon-delete"
            type="danger"
            size="small"
            @click="del(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
      <el-pagination
      style="padding-top: 15px;text-align: left;"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :v-if="total != 0"
      :current-page="currentPage"
      :page-sizes="[5,15,30,60]"
      :page-size="pageSize"
      :total="total"
      layout="total, sizes, prev, pager, next, jumper"
      
    ></el-pagination>
     </el-card>

    </div></body>
</template>

<script>
export default {

  data(){
    return{
      tableData:[],
      changeUserDialog:false,
      currentPage: 1,
      total: null,
      pageSize: 5,
      searchForm:{
        username: "",
        realname:"",
        password: "",
        email: "",
        choice:"全部",
      },
      ruleForm: {},
      rules: {
        username: [
          { required: true, message: "请输入账号", trigger: "blur" },
          { min: 2, message: "长度不能少于2个字符", trigger: "blur" },
          { max: 10, message: "长度不能超过10个字符", trigger: "blur" },
          {
            validator: function (rule, value, callback) {
              //校验空格
              let reg = new RegExp("[ ]");
              if (reg.test(value)) {
                callback(new Error("不能输入空格字符"));
              } else {
                //校验通过
                callback();
              }
            },
            
          },
        ],       
        realname: [
          { required: true, message: "请输入真实性名", trigger: "blur" },
          {
            validator: function (rule, value, callback) {
              //校验中文的正则：/^[\u4e00-\u9fa5]{0,}$/
              if (/^[\u4e00-\u9fa5]+$/.test(value) == false) {
                callback(new Error("请输入中文"));
              } else {
                //校验通过
                callback();
              }
            },
          },
        ],
        email: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
      },
    }
  },
  methods:{
    findAll(){
       var _this=this;
      axios.get('http://localhost:8181/user/findAll/'+_this.currentPage+'/'+_this.pageSize).then(function(resp){
            _this.tableData = resp.data.content;
            });
    },
    findAllByChoice(formName){
       var _this=this;
       formName = _this.searchForm.choice;
      axios.get('http://localhost:8181/user/findAllByChoice/'+_this.currentPage+'/'+_this.pageSize+'/'+formName).then(function(resp){
            _this.tableData = resp.data.content;
            });
    },
    //模糊查询
     search(){
        var _this=this;
        if(_this.searchForm.choice === "全部"){
          _this.searchForm.choice = "";
         var formName = _this.searchForm.realname;
        axios.get('http://localhost:8181/user/FuzzyQuery/'+_this.currentPage+'/'+_this.pageSize+'/'+formName).then(function(resp){
           _this.tableData = resp.data.content;
            });
        }else{
         var formName = _this.searchForm.realname;
        axios.get('http://localhost:8181/user/FuzzyQuery/'+_this.currentPage+'/'+_this.pageSize+'/'+formName+'/'+_this.searchForm.choice ).then(function(resp){
           _this.tableData = resp.data.content;
            });
        }
    },
    handleSizeChange(val) {
    // console.log(`每页 ${val} 条`);
    this.pageSize = val;
    this.findAll();
      },
    handleCurrentChange(val) {
    // console.log(`当前页: ${val}`);
    this.currentPage = val;
    this.findAll();
      },
    del(row) {
      this.$confirm("此操作将永久注销该账号信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 数据库...
          const _this = this;
          axios
            .delete("http://localhost:8181/user/deleteByUsername/" + row.username)
            .then(function (resp) {
              _this.$alert("账号 "+ row.username + " 删除成功", "消息", {
                confirmButtonTest: "确定",
                callback: (action) => {
                    window.location.reload();}
              });
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    delAll(){
       this.$confirm("此操作将永久删除所有账号信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 数据库...
          const _this = this;
          axios
            .delete("http://localhost:8181/user/deleteAll/")
            .then(function (resp) {
              _this.$alert("所有账号信息均已删除", "消息", {
                confirmButtonTest: "确定",
                callback: (action) => {
                    window.location.reload();}
              });
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    showByChoice(){
     var formName = this.searchForm.choice;
      switch(formName){
        
        case "全部":
          this.findAll();
          break;        
        default:
          this.findAllByChoice(formName);
          break;
        }
    },
  },
  created(){
    const _this = this;
    _this.findAll();
      
    var choice = window.sessionStorage.getItem("choice")
      if(choice != "人事管理员"){
        this.$router.push({name: "MasterPage"});
      }
  },


}
</script>

<style>

</style>