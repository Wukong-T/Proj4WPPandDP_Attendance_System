<template>
<body>

<div>
      <!-- 增加学生考勤信息 -->
      <el-dialog
        :visible.sync="insetAttendDialog"
        width="30%"
        style="align:center"
        :close-on-click-modal="false"
        @close="resetForm('insertForm')">
          <el-form
            :model="insertForm"
            :rules="rules"
            ref="insertForm"
            label-width="100px">

                <el-form-item label="工号" prop="username" >
                  <el-input
                    prefix-icon="el-icon-user-solid"
                    placeholder="请输入工号"
                    v-model.trim="insertForm.username"
                  ></el-input>
                </el-form-item>

                <el-form-item label="真实姓名" prop="realname">
                  <el-input
                    prefix-icon="el-icon-s-custom"
                    placeholder="请输入真实姓名"
                    v-model.trim="insertForm.realname"
                  ></el-input>
                </el-form-item>


                <el-form-item label="早退次数 " prop="early">
                  <el-input-number
                    v-model.number.trim="insertForm.early"
                    placeholder="请输入早退次数"
                    prefix-icon="el-icon-timer"

                  ></el-input-number>
                </el-form-item>

                <el-form-item label="迟到次数 " prop="late">
                  <el-input-number
                    v-model.number.trim="insertForm.late"
                    placeholder="请输入迟到次数"
                    prefix-icon="el-icon-timer"

                  ></el-input-number>
                </el-form-item>
                  
                <el-form-item label="旷工次数 " prop="truancy">
                  <el-input-number
                    v-model.number.trim="insertForm.truancy"
                    placeholder="请输入旷工次数"
                    prefix-icon="el-icon-timer"
                  ></el-input-number>
                </el-form-item>

                <el-form-item>
 
                  <el-row :span="12" :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                  <el-col>
                    <el-button
                      type="primary"
                      @click.prevent="insertAttend('insertForm')"
                      style="width:100%; font-size:18px"
                    >确认添加</el-button>
                  </el-col></el-row
                >
                </el-form-item>
          </el-form>
      </el-dialog>
      
      <!-- 修改学生考勤信息 -->
      <el-dialog
        :visible.sync="updateAttendDialog"
        width="30%"
        style="align:center"
        :close-on-click-modal="false"
        @close="resetForm('attendForm')">
          <el-form
            :model="attendForm"
            :rules="rules"
            ref="attendForm"
            label-width="100px">

                <el-form-item label="工号" prop="username" >
                   <el-tag  effect="plain"  type="info"  style="width:100%;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{attendForm.username}}</i> </el-tag>
                </el-form-item>

                <el-form-item label="真实姓名" prop="realname">
                  <el-tag  effect="plain"  type="info"  style="width:100%;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{attendForm.realname}}</i> </el-tag>
                </el-form-item>


                <el-form-item label="早退次数 " prop="early">
                  <el-input-number
                    v-model.number.trim="attendForm.early"
                    placeholder="请输入早退次数"
                    prefix-icon="el-icon-timer"

                  ></el-input-number>
                </el-form-item>

                <el-form-item label="迟到次数 " prop="late">
                  <el-input-number
                    v-model.number.trim="attendForm.late"
                    placeholder="请输入迟到次数"
                    prefix-icon="el-icon-timer"

                  ></el-input-number>
                </el-form-item>

                <el-form-item label="请假次数 " prop="vacation">
                  <el-input-number
                    disabled
                    v-model.number.trim="attendForm.vacation"
                    placeholder="请输入请假次数"
                    prefix-icon="el-icon-timer"
                  ></el-input-number>
                </el-form-item>
                  
                <el-form-item label="旷工次数 " prop="truancy">
                  <el-input-number
                    v-model.number.trim="attendForm.truancy"
                    placeholder="请输入旷工次数"
                    prefix-icon="el-icon-timer"
                  ></el-input-number>
                </el-form-item>

                <el-form-item>
 
                  <el-row :span="12" :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                  <el-col>
                    <el-button
                      type="primary"
                      @click.prevent="upadteAttend('insertForm')"
                      style="width:100%; font-size:18px"
                    >确认修改</el-button>
                  </el-col></el-row
                >
                </el-form-item>
          </el-form>
      </el-dialog>

        <!-- 查看学生反馈 -->
        <el-dialog
        :visible.sync="showProposeDataDialog"
        width="60%"
        style="align:center"
        :close-on-click-modal="false"
          @close="resetForm('attendForm')"
        >
        <el-row>
          <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
        <el-form :inline="true" :rules="rules" :model="attendForm" ref="attendForm" class="demo-form-inline" >
            <el-row > 

              <el-col :span="24" :xs="12" :sm="12" :md="24" :lg="24" :xl="24" >
                  <el-form-item label="姓名 " prop="realname"    label-width="50px">
                    <el-tag  effect="plain"  type="info"  style="width:250px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{attendForm.realname}}</i> </el-tag>
                </el-form-item>
                <el-form-item label="工号 " prop="username"  label-width="50px">
                  <el-tag  effect="plain"  type="info"  style="width:250px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{attendForm.username}}</i> </el-tag>
                </el-form-item>
              </el-col>

            <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" >
              <div style="text-align:center">
              <el-form-item label="意见反馈 " prop="propose">
                  <el-tag  effect="plain"  type="info"  style="height:64px;width:500px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{attendForm.propose}}</i> </el-tag>
                </el-form-item>
                </div>
            </el-col>

         </el-row>
        </el-form>
          </el-col>
        </el-row>

      </el-dialog>
</div>

    <el-card style="margin:5px;" class="box-card">
        <div style="text-align:left">
        <!-- 查询条件输入部分 -->
        <el-row><el-col>
         <el-form :inline="true" :rules="rules" :model="searchForm" class="demo-form-inline">
           <el-row>
          <el-form-item>
            <el-button round plain type="success" icon="el-icon-zoom-in" @click="insetAttendDialog = true"
              >新增员工考勤信息</el-button
            >
          </el-form-item>

          <el-form-item  label="按所在组号显示" prop="classes" >

            <el-select v-model="item" placeholder="请选择"  @change="showByChoice()" v-show="selectClass">
            <el-option v-for="item in classes.length" :key="classes[item-1]" :label="classes[item-1]" :value="classes[item-1]"></el-option>
             </el-select>

            <el-input style="width:100px; margin-left: 10px" v-model="item" v-show="selectDept"
            size="small" @keyup.enter.native="item" @blur="showByChoice"
            ></el-input>
         
        </el-form-item>

          <el-form-item  >
            <el-button size="small" plain  round type="primary"  @click="findAll()"> 显示全部</el-button>
          </el-form-item>

          <el-form-item  >
            <el-button size="small" type="primary" @click="exportExcel"> <i class="el-icon-download"></i>导出excel文件</el-button>
          </el-form-item>
          </el-row>  
        </el-form>

          </el-col></el-row>

      </div>
    </el-card>

    
    <el-card style="margin:5px;" class="box-card">
    <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
    <div style="text-align: left; ">
      <el-row>
     <el-col :span="16" :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
        <el-tag effect="dark" type="info"  style="font-size:15px; margin:24px;margin-bottom:20px;">
            <h2>学生考勤信息</h2>
        </el-tag>

   
            <el-input
              id="fname"
              clearable
              style="width: 150px"
              v-model="searchForm.realname"
              placeholder="输入查询姓名"
            ></el-input>

            <el-button round icon="el-icon-search" @click="search('searchForm')"
              >查询</el-button >

            <el-button size="small" style="margin-left: 10px;"  type="danger" 
            @click="delAll()" >删除全部考勤信息</el-button>
    

      </el-col>

          <el-col :span="8" :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
          <el-upload 
            action="" 
            :auto-upload="false" 
            :file-list="fileList" 
            :on-change="handleChange"
            accept=".xls,.xlsx">
            <el-button size="small"  style="margin-left: 10px;" slot="trigger" type="primary" @click="delFile">选取文件</el-button>
            <el-button size="small" style="margin-left: 10px;"  type="success" @click="submitUpload" :loading="uploadLoading">上传到服务器</el-button>
            <div slot="tip"  class="el-upload__tip"  style="margin-left: 10px;" >只能上传xls/xlsx文件</div>
          </el-upload>
        </el-col>


  </el-row>
    </div>
    <el-table class="mytable" :data="tableData" border >
      <el-table-column prop="realname" label="真实姓名" align="center" width="100px"></el-table-column>
      <el-table-column prop="username" label="组号" align="center" width="100px"></el-table-column>
      <el-table-column prop="classes" label="组号" align="center" width="100px"></el-table-column>
      <el-table-column prop="dept" label="部门" align="center" width="150px"></el-table-column>
      <el-table-column prop="early" label="早退次数" align="center" width="50px"></el-table-column>
      <el-table-column prop="late" label="迟到次数" align="center" width="50px"></el-table-column>
      <el-table-column prop="vacation" label="请假次数" align="center" width="50px"></el-table-column>
      <el-table-column prop="truancy" label="旷工次数" align="center" width="50px"></el-table-column>
      <el-table-column prop="score" align="center" width="100px"  label="考勤成绩" >
        <template slot-scope="scope">
        <el-tag effect="plain"
            :type="scope.row.score > 60 ? 'primary' : 'danger'"
            disable-transitions>{{scope.row.score}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope">
          <el-button size="small"  type="primary" style="margin-left: 10px;"  icon="el-icon-edit" @click="edit(scope.row)"
            >编辑</el-button>

          <el-button
            icon="el-icon-delete"
            type="danger"
            size="small"
            style="margin-left: 10px;" 
            @click="del(scope.row)"
            >删除</el-button>

            <el-button
            icon="el-icon-view"
            type="warning"
            size="small"
            style="margin-left: 10px;" 
            @click="showPropose(scope.row)"
            >查查反馈</el-button>
        </template>
      </el-table-column>
    </el-table>
   </el-col>
<el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
    <el-pagination
      style="padding-top: 15px;text-align: left;"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :v-if="total != 0"
      :current-page="currentPage"
      :page-sizes="[5,15,30,50]"
      :page-size="pageSize"
      :total="total"
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>
  </el-col>

      <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
      <div style="text-align: left; ">
        <el-row><el-col>
        <el-form :inline="true"  class="demo-form-inline">
            
          <el-form-item  >
              <div style="text-align:left;font-size:15px; margin:24px;margin-bottom:20px;">
                <el-tag  effect="plain" type="danger" style="height:100% ;text-align: left;" > 
                 <i style="text-align: left;font-size:12px;color:#000;">
                  (1) 员工的请假次数按员工实际请假次数而定。
                  (2) 员工考勤成绩默认满分为100;低于60分将预警；
                  (3) 迟到一次扣3分，早退扣2分，旷课扣5分。</i> </el-tag>
              </div>
          </el-form-item>
  
        </el-form>
      </el-col></el-row>
      </div>

    </el-col>
      </el-card>
</body>
</template>

<script>
export default {
    data(){
        return{
          tableData:[],
          classes:[],

          fileList:[],
          uploadLoading :false,
          fliename:'',

          insetAttendDialog:false,
          updateAttendDialog:false,
          showProposeDataDialog:false,

          selectClass:true,
          selectDept:false,
          
          currentPage: 1,
          total: null,
          pageSize: 5, 

          ruleForm:{
            username:""
          },
          searchForm:{
              realname:"",
          },
          item:"全部",
          insertForm:{
            realname:"",
            username: "",
            early:"",
            late:"",
            truancy:"",
          },
          rules: {
            username: [
              { required: true, message: "请输入账号", trigger: "blur" },
              { min: 10, message: "请输入正确的职工号", trigger: "blur" },
              { max: 10, message: "请输入正确的职工号", trigger: "blur" },
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
            early: [
              { required: true, message: "早退次数不能为空" ,trigger: "blur" },
              { type: "number", message: "早退次数为数字值" },
            ],
            late: [
              { required: true, message: "迟到次数不能为空" ,trigger: "blur" },
              { type: "number", message: "迟到次数必须为数字值" },
            ],  

            truancy: [
              { required: true, message: "旷工次数不能为空" ,trigger: "blur" },
              { type: "number", message: "旷工次数必须为数字值" },
            ],          


          },
          attendForm:{

          },
        }
    },
    methods:{
          //重置
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      //模糊查询
      search(){
        var _this=this;
        if(_this.item === "全部"){
         var formName = _this.searchForm.realname;
        axios.get('http://localhost:8181/attend/FuzzyQuery/'+_this.currentPage+'/'+_this.pageSize+'/'+formName).then(function(resp){
           _this.tableData = resp.data.content;
            });
        }else{
         var formName = _this.searchForm.realname;
        axios.get('http://localhost:8181/attend/FuzzyQuery/'+_this.currentPage+'/'+_this.pageSize+'/'+formName+'/'+_this.item ).then(function(resp){
           _this.tableData = resp.data.content;
            });
        }
      },
      delAll(){
          this.$confirm("此操作将永久删除所有学生的考勤信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 数据库...
          const _this = this;
          axios
            .delete("http://localhost:8181/attend/deleteAttend/")
            .then(function (resp) {
              _this.$alert(" 所有的考勤信息已删除！", "消息", {
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
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`);
        this.pageSize = val;
        this.showByChoice();
        },
      handleCurrentChange(val) {
        // console.log(`当前页: ${val}`);
        this.currentPage = val;
        this.showByChoice();
        },
        showPropose(row){
           this.showProposeDataDialog = true;
         const _this = this;
        //  resetForm('attendForm')
        axios.get('http://localhost:8181/attend/findAllByUsername/'+row.username).then(function(resp){
                      _this.attendForm = resp.data;
          }); 
        },
        findAll(){
           const _this = this;
            _this.ruleForm.username = window.sessionStorage.getItem("username");
           axios.get('http://localhost:8181/attend/findAll/'+_this.currentPage+'/'+_this.pageSize+'/'+_this.ruleForm.username).then(function(resp){
              _this.tableData = resp.data.content;

          var choice = window.sessionStorage.getItem("choice");

          if(choice ===  "部门经理"){
              axios.get('http://localhost:8181/data/findAllByUsername/'+_this.ruleForm.username).then(function(resp){
                  _this.ruleForm = resp.data
                 
              axios.get('http://localhost:8181/leave/findClass/'+_this.ruleForm.realname).then(function(resp){
                      _this.classes = resp.data;
                }); 
            });
          }else{
            _this.selectClass =false;
            _this.selectDept = true;
            _this.item = '';
           
          }
        }); 
        },
    insertAttend(formName){
      const _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
            axios.post("http://localhost:8181/attend/insertAttend",_this.insertForm)
            .then(function (resp) {

              switch(resp.data){
                
                case "empty":
                  _this.$message.error("该员工信息不存在!");
                  // _this.$refs["ruleForm"].resetFields();
                break;

                case "repeat":
                  _this.$message.error("该考勤信息已存在!");
                  // _this.$refs["ruleForm"].resetFields();
                break;

                case "success":
                    window.location.reload();
                  _this.$message({
                    message: "添加成功",
                    type: "success",
                  });
                break;  
              }

            })
            .catch((failResponse) => {});
        } else {
          return false;
        }
      });
    },
      // 导出Excel文件
      exportExcel() {
        require.ensure([], () => {
          const { export_json_to_excel } = require('../../vendor/Export2Excel');//此处把路径要写对
          const tHeader = ['真实姓名','工号', '组号', '部门','早退次数', '迟到次数','请假次数','旷工次数','考勤成绩'];
          // 上面设置Excel的表格第一行的标题
          const filterVal = ['realname','username', 'classes', 'dept','early','late','vacation','truancy','score'];
          // 上面的index、nickName、name是tableData里对象的属性
          const list = this.tableData;  //把data里的tableData存到list
          const data = this.formatJson(filterVal, list);
          export_json_to_excel(tHeader, data, '员工考勤信息表');//"列表excel"  是下载后的表名 可修改
        })
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => {
          return filterVal.map(j => v[j])
        })
      },
      showByChoice(){
        if(this.item === "全部"||this.item === ''){
            this.findAll();
        }else{
          const _this = this;
          var username = window.sessionStorage.getItem("username")
          axios.get('http://localhost:8181/attend/findByClass/'+_this.currentPage+'/'+_this.pageSize+'/'+this.item +'/'+ username).then(function(resp){
              _this.tableData = resp.data.content; });
        }

      },
    //编辑（提交）按钮
    upadteAttend(formName) {
      const _this = this;      
        this.$confirm('是否确定修改？', '温馨提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

        //修改用户基础信息
        axios.post("http://localhost:8181/attend/updateAttend", _this.attendForm)
        .then(function (resp) {

          switch(resp.data){
            case "empty":
                _this.$message.error("该用户信息不存在！");
          
            break;

            case "success":
              _this.$message({message: "修改成功",type: "success",});    
              window.location.reload();
            break;  
          }
        })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退出'
          });          
        });
    },
    //编辑
    edit(row){
        this.updateAttendDialog = true;
         const _this = this;
        //  resetForm('attendForm')
        axios.get('http://localhost:8181/attend/findAllByUsername/'+row.username).then(function(resp){
                      _this.attendForm = resp.data;
          }); 
      },
    //删除
    del(row) {
      this.$confirm("此操作将永久删除该用户考勤信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 数据库...
          const _this = this;
          axios
            .delete("http://localhost:8181/attend/deleteAttend/" + row.username)
            .then(function (resp) {
              _this.$alert(" “ " + row.realname + " ” 的考勤信息已删除！", "消息", {
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
      delFile() {
      this.fileList = [];
       this.uploadLoading = false;

    },
    //文件状态改变时的钩子函数
    handleChange(file, fileList) {
    
        //  console.log(file.name)
        if(file.name === "学生考勤信息表.xlsx"||file.name === "学生考勤信息表.xls"){
            this.fileList = fileList;
        }else{
            this.$message.error("只能上传学生考勤信息表！");
          this.delFile()
        }
    },
    //单击上传到服务器（提交）按钮
  submitUpload() {
      if (this.fileList.length == 0) {
        return this.$message.error("请选择文件");
      }
    const _this = this;
    _this.uploadLoading = true;
      let formData = new FormData();//文件转换
      //fileList 对应你服务器接受名称
      formData.append("fileList", this.fileList[0].raw);
      //这边"$http"我重新封装了axios,你可以改成自己的.但是必须是post来请求,
      //http://localhost:8082/yiban_zlj/fileUpload改成你服务器文件上传的API地址
      //formData请求带的数据
      //{"Content-Type": "multipart/form-data;charset=utf-8"}是文件上传必须要的
      //最后可以打印返回的服务器返回的数据,如果控制台报错接受你哪里没有写对
      axios.post('http://localhost:8181/attend/fileUpload', 
      formData, {"Content-Type": "multipart/form-data;charset=utf-8"}).then(function (resp) {
        console.log(resp.data)
      switch(resp.data){

              case "success":
                _this.uploadLoading = false;
                _this.$message({message: "录入成功！",type: "success",});    
                break;  

              case "repeat":
                  _this.uploadLoading = false;
                  _this.$message.warning("考勤信息存在重复且已更新！");
                  break;
              }
        setTimeout(()=>{
            //需要延迟的代码 :一定时间后延迟刷新界面（留取一定时间提示）
              window.location.reload();},500)//延迟时间：0.5秒
        })
    },
    },
    created(){
        this.findAll();
    }
}
</script>

<style>

</style>