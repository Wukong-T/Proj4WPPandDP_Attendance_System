<template>
  <body>
    <div>
      <!-- 修改该辅导员信息 -->
      <el-dialog
        :visible.sync="updateDataDialog"
        width="30%"
        style="align:center"
        :close-on-click-modal="false"
          @close="resetForm('ruleForm')"
        >
          <el-form
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            label-width="100px">
                <el-form-item label="真实姓名" prop="realname">
                  <el-input
                    prefix-icon="el-icon-s-custom"
                    placeholder="请输入真实姓名"
                    v-model.trim="ruleForm.realname"
                  ></el-input>
                </el-form-item>

                <el-form-item label="性别" prop="sex" >
                    <div style="text-align: left">
                    <el-radio-group v-model="ruleForm.sex">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                    </el-radio-group>
                    
                    </div>
                </el-form-item>

                <el-form-item label="年龄 " prop="age">
                  <el-input
                    v-model.number.trim="ruleForm.age"
                    placeholder="请输入年龄"
                    prefix-icon="el-icon-timer"
                    min="1"
                    max="100"
                  ></el-input>
                </el-form-item>

                <el-form-item label="工号" prop="username" >
                  <el-input
                    disabled
                    prefix-icon="el-icon-user-solid"
                    placeholder="请输入账号（工号）"
                    v-model.trim="ruleForm.username"
                  ></el-input>
                </el-form-item>
                
              <!-- <el-form-item label="所管组号: "  label-width="150px">
                <div style="text-align:left;width:400px;" >
                  
                <el-tag :key="tag"  v-for="tag in classes" style=" margin-left: 10px" closable :disable-transitions="false"  
                @close="handleClose(tag)" type="info" >{{tag}}</el-tag>
                
                <el-input style="width:100%; margin-left: 10px" class="input-new-tag"
                v-if="inputVisible" v-model="inputValue" ref="saveTagInput"
                size="small" @keyup.enter.native="handleInputConfirm" @blur="handleInputConfirm"
                ></el-input>
                <el-button v-else style="width:100px; margin-left: 10px" class="button-new-tag" size="small" 
                @click="showInput"><i>添加组号</i></el-button>

                </div>
                </el-form-item> -->

                <el-form-item label="部门 " prop="dept">
                  <el-input
                    placeholder="请输入部门"
                    prefix-icon="el-icon-office-building"
                    v-model.trim="ruleForm.dept"
                  ></el-input>
                </el-form-item>
                
                
            <el-form-item label="选择用户 " prop="choice" v-if="select =false">
              <el-input
              disabled
                placeholder="请输入选择用户"
                v-model.trim="ruleForm.choice"
              ></el-input>
            </el-form-item>

                <el-form-item>
 
                <el-row :span="12" :offset="6">
                  <el-col>
                    <el-button
                      type="primary"
                      @click.prevent="editData('ruleForm')"
                      style="width:100%; font-size:18px"
                    >
                     确认修改
                    </el-button>
                  </el-col></el-row
                >
                </el-form-item>
          </el-form>
      </el-dialog>

      <!-- 增加用户信息 -->
      <el-dialog
        :visible.sync="insetDataDialog"
        width="30%"
        style="align:center"
        :close-on-click-modal="false"
        @close="resetForm('insertForm')">
          <el-form
            :model="insertForm"
            :rules="rules"
            ref="insertForm"
            label-width="100px">

                <el-form-item label="职工号" prop="username" >
                  <el-input
                    prefix-icon="el-icon-user-solid"
                    placeholder="请输入职工号"
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

                <el-form-item label="性别" prop="sex" >
                    <div style="text-align: left">
                    <el-radio-group v-model="insertForm.sex">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                    </el-radio-group>
                    
                    </div>
                </el-form-item>

                <el-form-item label="年龄 " prop="age">
                  <el-input
                    v-model.number.trim="insertForm.age"
                    placeholder="请输入年龄"
                    prefix-icon="el-icon-timer"
                    min="1"
                    max="100"
                  ></el-input>
                </el-form-item>

                <el-form-item label="所管组号: ">
                <div style="text-align:left;" >
                  
                <el-tag :key="tag"  v-for="tag in classes"  closable :disable-transitions="false"  
                @close="handleClose(tag)" type="info" >{{tag}}</el-tag>
                
                <el-input style="width:100px; margin-left: 10px" class="input-new-tag"
                v-if="inputVisible" v-model="inputValue" ref="saveTagInput"
                size="small" @keyup.enter.native="handleInputConfirm" @blur="handleInputConfirm"
                ></el-input>
                <el-button v-else style="width:100px; margin-left: 10px" class="button-new-tag" size="small" 
                @click="showInput"><i>添加组号</i></el-button>

                </div>
                </el-form-item>

                <el-form-item label="院系 " prop="dept">
                  <el-input
                    placeholder="请输入院系"
                    prefix-icon="el-icon-office-building"
                    v-model.trim="insertForm.dept"
                  ></el-input>
                </el-form-item>
                
                <el-form-item label= "选择用户" prop="choice" v-if="select = false">
                  <el-input
                    disabled
                    placeholder="请输入选择用户"
                    v-model.trim="insertForm.choice"
                  ></el-input>
                </el-form-item>

                <el-form-item>
 
                <el-row :span="12" :offset="6">
                  <el-col>
                    <el-button
                      type="primary"
                      @click.prevent="insertData('insertForm')"
                      style="width:100%; font-size:18px"
                    >确认添加</el-button>
                  </el-col></el-row
                >
                </el-form-item>
          </el-form>
      </el-dialog>

      <!-- 班级管理 -->
      <el-dialog
        :visible.sync="editClassDataDialog"
        width="60%"
        style="align:center"
        :close-on-click-modal="false"
          @close="resetForm('ruleForm')"
        >

            <el-form :inline="true" :rules="rules" :model="ruleForm" ref="ruleForm" class="demo-form-inline" >
            <el-row > 
            
          <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <el-form-item label="所管班级: "  label-width="150px">
            <div style="text-align:left;width:400px;" >
              
            <el-tag :key="tag"  v-for="tag in classes" style=" margin-left: 10px" closable :disable-transitions="false"  
            @close="handleClose(tag)" type="info" >{{tag}}</el-tag>
            
            <el-input style="width:100px; margin-left: 10px" class="input-new-tag"
            v-if="inputVisible" v-model="inputValue" ref="saveTagInput"
            size="small" @keyup.enter.native="handleInputConfirm" @blur="handleInputConfirm"
            ></el-input>
            <el-button v-else style="width:100px; margin-left: 10px" class="button-new-tag" size="small" 
            @click="showInput"><i>添加班级</i></el-button>

            </div>

              <div style="text-align:left;font-size:12px; margin:24px;margin-bottom:20px;">
                <el-tag  effect="plain" type="danger" style="height:100% ;text-align: left;" > 
                 <i style="text-align: left;font-size:12px;color:#000;">
                    （1）可通过“添加班级”按钮添加班级<br>
                    （2）点击X号可直接解除该老师对该班级的管理<br></i> </el-tag>
              </div>
            </el-form-item>
    
        </el-col>

         </el-row>
        </el-form>
        

      </el-dialog>

    </div>
    <div>

      <el-card>
        <div style="text-align:left">
        <!-- 查询条件输入部分 -->
        <el-form :inline="true" :model="searchForm" class="demo-form-inline">

          <el-form-item>
            <el-button round plain type="primary" icon="el-icon-zoom-in" @click="insetDataDialog = true"
              >新增辅导员信息</el-button
            >
          </el-form-item>

          <el-form-item
            label-width="50px"
            label-position="labelPosition"
          >
            <el-input
              id="fname"
              clearable
              style="width: 150px"
              v-model="searchForm.realname"
              placeholder="输入查询姓名"
            ></el-input>
          </el-form-item>
        <el-form-item>
            <el-button round icon="el-icon-search" @click="search('searchForm')"
              >查询</el-button
            >
          </el-form-item>
  
          <el-form-item  >
            <el-button size="small" type="primary" @click="exportExcel"> <i class="el-icon-download"></i>导出excel文件</el-button>
          </el-form-item>
   

        </el-form>
      </div>
      <el-row>
        <el-col :span="17">
    <el-table class="mytable" :data="tableData" border style="width: 100%">
      <!-- <el-table-column type="index" label="序号"  width="50px" ></el-table-column> -->
      <el-table-column prop="realname" label="真实姓名" align="center" width="100px"></el-table-column>
      <el-table-column prop="sex"  label="性别" align="center" width="50px"></el-table-column>
       <el-table-column prop="age" label="年龄" align="center" width="50px"></el-table-column>
       <el-table-column prop="username" label="学号/职工号" align="center" width="100px"></el-table-column>
      <el-table-column label="班级"  align="center"  width="100px"  >

        <template slot-scope="scope">
            <el-link
              type="primary"
              @click.prevent="editClass(scope.row)"
              style="padding:15px "> 查看</el-link>

        </template>
      </el-table-column>
      <!-- <el-table-column prop="classes" label="班级"  width="100px"></el-table-column> -->
      <el-table-column prop="dept" label="院系" align="center" width="150px"></el-table-column>
      <el-table-column prop="choice" width="100px" align="center" label="用户类型"></el-table-column>
      
      <el-table-column label="操作" >

        <template slot-scope="scope">
          <el-button size="small" type="primary" icon="el-icon-edit" @click="edit(scope.row)"
            >编辑</el-button>
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
</el-col>
   <el-col :span="4">
      <div style="text-align:left">
      <el-upload 
      action="" 
      :auto-upload="false" 
      :file-list="fileList" 
      :on-change="handleChange"
      accept=".xls,.xlsx">
        <el-button size="small" style="margin-left: 10px;"  slot="trigger" type="primary" @click="delFile">选取文件</el-button>
        <el-button size="small" style="margin-left: 10px;"  type="success" @click="submitUpload" :loading="uploadLoading">上传到服务器</el-button>
        <div slot="tip" style="margin-left: 10px;"  class="el-upload__tip">只能上传xls/xlsx文件</div>
      </el-upload>
      </div>
          <el-button size="small" style="margin-left: 10px;margin:30PX"  type="danger" 
            @click="delAll()" >删除全部辅导员信息</el-button>
</el-col>
    </el-row>

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

    </div>
   </body>
</template>

<script>

export default {

  data(){

    return{
      //导入导出相关
      fileList:[],
      uploadLoading :false,
      tableData:[],
      fliename:'',

      updateDataDialog:false,
      insetDataDialog:false,
      editClassDataDialog:false,

      //分页相关
      currentPage: 1,
      total: null,
      pageSize: 5,

    //班级管理
      classes:[],
      inputVisible: false,
      inputValue: '',
    
    //录入信息
      insertForm:{
        realname:"",
        username: "",
        age:"",
        sex:"",
        depts:"", 
        choice:"辅导员",
      },
      //查询信息
      searchForm:{
          realname:"", 
          choice:"辅导员",
      },
      // 用户信息
      ruleForm: {},

      rules: {
        username: [
          { required: true, message: "请输入职工号", trigger: "blur" },
          { min: 10, message: "请输入正确的职工号", trigger: "blur" },
          { max: 10, message: "请输入正确的职工号", trigger: "blur" },
          {
            validator: function (rule, value, callback) {
              //校验空格
              let reg = new RegExp("[ ]");
              if (reg.test(value)) {
                callback(new Error("不能输入空格字符"));
              }else {
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
        age: [
          { required: true, message: "年龄不能为空" ,trigger: "blur" },
          { type: "number", message: "年龄必须为数字值" },
        ],        
        dept: [
          { required: true, message: "院系不能为空！", trigger: "blur"},
        ],
        // classes: [
        //  { required: true, message: "班级不能为空！", trigger: "blur"},
        // ],
        sex: [
          { required: true, message: "性别不能为空", trigger: "blur" },]
      },
    }
  },
  methods:{
    
    //删除班级
    handleClose(tag) {
        // console.log(tag);
        this.$confirm(this.ruleForm.realname+"将不再管理"+tag+"班, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 数据库...
          const _this = this;
          axios
            .delete("http://localhost:8181/leave/delClass/" + tag)
            .then(function (resp) {
              _this.$alert(" 删除成功！", "消息", {
                // confirmButtonTest: "确定",
                callback: (action) => {
                   this.classes.splice(this.classes.indexOf(tag), 1);
                    // window.location.reload();
                  }
              
              });
                window.location.reload();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
        delAll() {
      this.$confirm("此操作将永久删除所有辅导员信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 数据库...
          const _this = this;
        
          axios
            .delete("http://localhost:8181/data/deleteDataByChoice/辅导员")
            .then(function (resp) {
              _this.$alert(" 所有辅导员的基本信息均已删除！", "消息", {
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
    //显示输入框
    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    //添加班级
    handleInputConfirm() {
      let inputValue = this.inputValue;  
      if (inputValue) {
       
        // console.log(this.ruleForm.realname)
        const _this = this
        axios.post("http://localhost:8181/leave/insertClass/",{
              checkname: _this.ruleForm.realname,
              classes:inputValue
           })
        .then(function (resp) {
        switch(resp.data){

          case "empty":
            _this.$message.error("辅导员姓名不能为空！");
             window.location.reload();
          case "repeat":
            
            _this.$message.error("班级已有人管理!不可重复添加！");
             window.location.reload();
            // _this.$refs["ruleForm"].resetFields();
          break;

          case "success":
            _this.$message({
              message: "添加成功",
              type: "success",
            });
            window.location.reload();
          break;  
            }
        })
        this.classes.push(inputValue);
      }
    
      this.inputVisible = false;
      this.inputValue = '';
    },


    //重置
    resetForm(formName) {
          this.$refs[formName].resetFields();
          this.classes = "";
      },

    findAllByChoice(formName){
       var _this=this;
       formName = _this.searchForm.choice;
      axios.get('http://localhost:8181/data/findAllByChoice/'+_this.currentPage+'/'+_this.pageSize+'/'+formName).then(function(resp){
            _this.tableData = resp.data.content;
            });
    },
    insertData(formName){
          const _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
            axios.post("http://localhost:8181/data/insertData",_this.insertForm)
            .then(function (resp) {

              switch(resp.data){


                case "repeat":
                  _this.$message.error("该用户信息已存在!");
                  // _this.$refs["ruleForm"].resetFields();
                break;

                case "success":
                  _this.$message({
                    message: "添加成功",
                    type: "success",
                  });
                  window.location.reload();
                break;  

              }

            })
            .catch((failResponse) => {});
        } else {
          return false;
        }
      });
    },
    //模糊查询
     search(){
        var _this=this;
        if(_this.searchForm.choice === "全部"){
          _this.searchForm.choice = "辅导员";
         var formName = _this.searchForm.realname;
        axios.get('http://localhost:8181/data/FuzzyQuery/'+_this.currentPage+'/'+_this.pageSize+'/'+formName).then(function(resp){
           _this.tableData = resp.data.content;
            });
        }else{
         var formName = _this.searchForm.realname;
        axios.get('http://localhost:8181/data/FuzzyQuery/'+_this.currentPage+'/'+_this.pageSize+'/'+formName+'/'+_this.searchForm.choice ).then(function(resp){
           _this.tableData = resp.data.content;
            });
        }
    },

    handleSizeChange(val) {
    // console.log(`每页 ${val} 条`);
    this.pageSize = val;
    this.findAllByChoice();
      },
    handleCurrentChange(val) {
    // console.log(`当前页: ${val}`);
    this.currentPage = val;
    this.findAllByChoice();
      },
    editData(formName) {
      const _this = this;
      this.$refs[formName].validate((valid) => {
              if (valid) {           
        this.$confirm('是否确定修改？', '温馨提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

        //修改用户基础信息
        axios.post("http://localhost:8181/data/updateData", _this.ruleForm)
        .then(function (resp) {

            switch(resp.data){
            case "empty":
                _this.$message.error("该用户信息不存在！");
          
            break;

            case "success":
            // window.sessionStorage.setItem("loginuser",_this.ruleForm.username);
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
        } else {
          _this.$message.error("请检查输入的信息是否完整！");
          return false;
        }
      });
    },
    del(row) {
      this.$confirm("此操作将永久删除该用户信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 数据库...
          const _this = this;
          axios
            .delete("http://localhost:8181/data/deleteData/" + row.username)
            .then(function (resp) {
              _this.$alert(" “ "+row.realname + "”的基本信息已删除！", "消息", {
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
    edit(row){
      this.updateDataDialog=true;
       const _this = this;
      _this.findAllByUsername(row);
      _this.findClass(row);
    },
    editClass(row){
      this.editClassDataDialog=true;
      const _this = this;
      _this.findAllByUsername(row);
      _this.findClass(row);
      
    },
    
    findAllByUsername(row){
       const _this = this;
      axios.get('http://localhost:8181/data/findAllByUsername/'+row.username ).then(function(resp){
        _this.ruleForm = resp.data});
    },
      findClass(row){
        const _this = this;
        axios.get('http://localhost:8181/leave/findClass/'+row.realname).then(function(resp){
        _this.classes = resp.data;}); 
    },

    delFile() {
      this.fileList = [];
       this.uploadLoading = false;
    },
	//文件状态改变时的钩子函数
	handleChange(file, fileList) {
	      //  console.log(file.name)
       if(file.name === "辅导员信息表.xlsx"||file.name === "辅导员信息表.xls"){
         	this.fileList = fileList;
       }else{
          this.$message.error("只能上传辅导员信息表！");
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
        axios.post('http://localhost:8181/data/fileUpload', 
        formData, {"Content-Type": "multipart/form-data;charset=utf-8"}).then(function (resp) {
          
        switch(resp.data){
                case "error":
                    _this.uploadLoading = false;
                    _this.$message.error("excel表格不符合规范！");
                    window.location.reload();
                break;

                case "success":
                      _this.uploadLoading = false;
                  _this.$message({message: "录入成功！",type: "success",});    
                  window.location.reload();
                  break;  

                case "repeat":
                    _this.uploadLoading = false;
                    _this.$message.warning("用户信息存在重复且已更新！");
                    window.location.reload();
                    break;
                }
        })
      },

    exportExcel() {
          require.ensure([], () => {
            const { export_json_to_excel } = require('../../vendor/Export2Excel');//此处把路径要写对
            const tHeader = ['真实姓名', '性别', '年龄','职工号', '班级', '院系','用户类型'];
            // 上面设置Excel的表格第一行的标题
            const filterVal = ['realname', 'sex', 'age','username', 'classes', 'dept','choice'];
            // 上面的index、nickName、name是tableData里对象的属性
            const list = this.tableData;  //把data里的tableData存到list
            const data = this.formatJson(filterVal, list);
            export_json_to_excel(tHeader, data, '辅导员信息表');//"列表excel"  是下载后的表名 可修改
          })
        },
    formatJson(filterVal, jsonData) {
       return jsonData.map(v => {
        return filterVal.map(j => v[j])
       })
    }

},
created(){
  const _this = this;
  _this.findAllByChoice();
  
    var choice = window.sessionStorage.getItem("choice")
      if(choice === "管理员"||choice === "教务处"){
        
      }else{
        this.$router.push({name: "MasterPage"});
      }
},

}
</script>

<style>

</style>