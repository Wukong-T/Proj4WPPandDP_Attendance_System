<template>
  <body>
    <div>
      <!-- 审核弹出框 -->
      <el-dialog
        :visible.sync="examLeaveDialog"
        width="80%"
        style="align:center"
        :close-on-click-modal="false"
        @close="resetForm('ruleForm')"
        >
        <el-row>
          <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                   <div style="text-align:center;" ><!--border:1px solid #000 -->
          <el-form :inline="true" :model="ruleForm" ref="ruleForm" class="demo-form-inline" >
            <el-row > 
                <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" >
                 <h2 style="font-size:20px; margin:12px;text-align: center; margin-bottom:20px;">
                    请假申请表</h2>
              </el-col>

              <el-col :span="24" :xs="12" :sm="12" :md="24" :lg="24" :xl="24" >
                  <el-form-item label="姓名 " prop="realname"    label-width="50px">
                    <el-tag  effect="plain"  type="info"  style="width:250px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{ruleForm.realname}}</i> </el-tag>
                </el-form-item>
                <el-form-item label="工号 " prop="username"  label-width="50px">
                     <el-tag  effect="plain"  type="info"  style="width:250px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{ruleForm.username}}</i> </el-tag>
                </el-form-item>
              </el-col>
               <el-col :span="24" :xs="12" :sm="12" :md="24" :lg="24" :xl="24">
                <el-form-item label="组号 " prop="classes"    label-width="50px">
                    <el-tag  effect="plain"  type="info"  style="width:250px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{ruleForm.classes}}</i> </el-tag>
              </el-form-item>
              <el-form-item label="部门 " prop="dept"  label-width="50px">
                  <el-tag  effect="plain"  type="info"  style="width:250px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{ruleForm.dept}}</i> </el-tag>
              </el-form-item>
            </el-col>

            <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form-item label="请假时间" prop="duration"  label-width="100px">
                    <div style="text-align: left">
                    <el-radio-group v-model="ruleForm.duration" disabled >
                        <el-radio label="一天内"></el-radio>
                        <el-radio label="一天以上三天以内" ></el-radio>
                        <el-radio label="三天以上一周以内"></el-radio>
                        <el-radio label="一周以上四周以内"></el-radio>
                    </el-radio-group>
                    </div>
                </el-form-item>
            </el-col>

            <el-col :span="24" :xs="12" :sm="12" :md="24" :lg="24" :xl="24">
                <el-form-item label="起始时间 " prop="startTime"    label-width="100px">
                  <div class="block">
                    <el-date-picker
                      disabled
                      size="small"
                      style="width:150px ;"
                      v-model.trim="ruleForm.startTime"
                      type="date"
                      placeholder="选择日期">
                    </el-date-picker>
                  </div>
              </el-form-item>
              <el-form-item label="结束时间 " prop="endTime"  label-width="100px">
                  <div class="block">
                    <el-date-picker
                      disabled
                      size="small"
                      style="width:150px ;"
                      v-model.trim="ruleForm.endTime"
                      type="date"
                      placeholder="选择日期">
                    </el-date-picker>
                  </div>
              </el-form-item>
            </el-col>

            <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <div style="text-align:center">
              <el-form-item label="请假原因 " prop="reason">

               <el-tag  effect="plain" type="info"  style="height:64px;width:500px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{ruleForm.reason}}</i> </el-tag>

                </el-form-item>
                </div>
            </el-col>

            <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-show="selectAuditTeacher">
              <div style="text-align:center">
              <el-form-item label="部门经理审核意见 " prop="auditTeacher">
                    <el-input
                    style="width:500px ;"
                      type="textarea"
                      placeholder="请输入部门经理审核意见"
                      v-model.trim="ruleForm.auditTeacher"
                      maxlength="1000"
                      show-word-limit
                    >
                    </el-input>
                </el-form-item>
                </div>
            </el-col>

              <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-show="selectAuditLeader">
              <div style="text-align:center">
              <el-form-item label="副经理审核意见 " prop="auditLeader">
                    <el-input
                    style="width:500px ;"
                      type="textarea"
                      placeholder="请输入副经理审核意见"
                      v-model.trim="ruleForm.auditLeader"
                      maxlength="1000"
                      show-word-limit
                    >
                    </el-input>
                </el-form-item>
                </div>
            </el-col>

              <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-show="selectAuditMaster">
              <div style="text-align:center">
              <el-form-item label="总经理审核意见 " prop="auditMaster">
                    <el-input
                    style="width:500px ;"
                      type="textarea"
                      placeholder="请输入总经理审核意见"
                      v-model.trim="ruleForm.auditMaster"
                      maxlength="1000"
                      show-word-limit
                    >
                    </el-input>
                </el-form-item>
                </div>
            </el-col>

            <el-col :span="24" :xs="12" :sm="12" :md="24" :lg="24" :xl="24">

              <el-form-item label=" " prop="state"  label-width="50px">
                    <div style="text-align: left">
                    <el-radio-group v-model="ruleForm.state"   size="small" fill=green >
                         <el-radio-button label="审核通过"></el-radio-button>
                    </el-radio-group>
                    <el-radio-group v-model="ruleForm.state"  size="small" fill=red >
                         <el-radio-button  label="审核未通过"></el-radio-button>
                    </el-radio-group>
                    </div>
              </el-form-item>

              <el-form-item label=" " prop="state"  label-width="50px">
                    <div style="text-align: left">
                   <el-button type="primary" size="small"  @click.prevent="examLeave('ruleForm')">确认</el-button>
                    </div>
              </el-form-item>
            </el-col>
         </el-row>
        </el-form>
        </div>
          </el-col>
        </el-row>

      </el-dialog>

    </div>
      <el-card>
    <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">   
    <div style="text-align: left; ">
        <el-tag effect="dark" type="info"  style="font-size:15px; margin:24px;margin-bottom:20px;">
          <h2>请假审核</h2>
        </el-tag>
    </div>
 

    <el-table class="mytable" :data="tableData" border >
      <!-- <el-table-column prop="frequency" label="序号" align="center" width="50px" ></el-table-column> -->
        <el-table-column prop="realname" align="center" width="100px" label="真实姓名"></el-table-column>
        <el-table-column prop="username"  align="center" width="100px" label="工号"></el-table-column>
        <el-table-column prop="duration"  align="center" width="100px" label="请假时长" ></el-table-column>
        <el-table-column prop="startTime" align="center" width="100px"  label="起始时间"
        :formatter="dataFormat"  ></el-table-column>
        <el-table-column prop="endTime" align="center" width="100px"  label="结束时间" 
        :formatter="dataFormat"></el-table-column>
        <el-table-column prop="reason" align="center" width="100px"  label="请假原因"></el-table-column>
          <el-table-column prop="checkname" align="center" width="100px"  label="审批人"></el-table-column>
        <el-table-column prop="state" align="center" width="100px"  label="当前状态" >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.state === '审核通过' ? 'success' : 'warning'
              &&scope.row.state === '审核未通过' ? 'danger' : 'warning'
              && scope.row.state === '审核中' ? 'info' : 'warning'"
              disable-transitions>{{scope.row.state}}</el-tag>
          </template>
        </el-table-column>
      <el-table-column label="操作" >
          <template slot-scope="scope">
          <el-button
          style="margin-left: 10px;"
            icon="el-icon-edit"
            type="primary"
            size="small"
            @click="updateLeave(scope.row)"
            >审核</el-button>

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
              <div style="text-align:left;font-size:12px; margin:24px;margin-bottom:20px;">
                <el-tag  effect="plain" type="danger" style="height:100% ;text-align: left;" > 
                 <i style="text-align: left;font-size:12px;color:#000;">
                  （1）请假原则上每学期不能超过四周，必须由员工本人提前申请。<br>
                  （2）请假时间在一天内或者一天以上三天以内的，由部门经理批准；<br>
                  （3）请假时间在三天以上一周以内的部门经理审核后提交给副经理批准;<br>
                  （4）请假时间在一周以上四周以内的在部门经理和副经理审核后交由总经理批准。</i> </el-tag>
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
      return {
        examLeaveDialog:false,//审核弹出框

        selectAuditTeacher:false,
        selectAuditLeader:false,
        selectAuditMaster:false,
            
        currentPage: 1,
        total: null,
        pageSize: 5,
        
        ruleForm:{},
        tableData:[],

      rules:{
        state:[{ required: true, message: "请确认审核", trigger: "blur" }],
      }
    }

  },
  methods:{
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.findAllOldLeave();
    },
    handleCurrentChange(val) {
    // console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.findAllOldLeave();
    },
    //修改时间格式
    dataFormat: function(row,column){
        let data = row[column.property]
    if (data ===null) {
      return ''
    }
    let dt = new Date(data)
    // console.log("dt"+dt);
      return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate()
      // + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds()
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    updateLeave(row){
      this.examLeaveDialog = true;
      this.findById(row);
    },

    findById(row){
       const _this = this;
      axios.get('http://localhost:8181/leave/findAllById/'+row.id ).then(function(resp){
          _this.ruleForm = resp.data      
        });
    },
    findExamLeave(){
     const _this = this;
      _this.username = window.sessionStorage.getItem("username");
      axios.get('http://localhost:8181/leave/showExamLeave/'+_this.currentPage+'/'+_this.pageSize+'/'+_this.username).then(function(resp){
                _this.tableData = resp.data.content;
            }); 
    },
    examLeave(formName){
        const _this = this;
        this.$refs[formName].validate((valid) => {
        if (valid) {           
        this.$confirm('是否确定修改并发送？', '温馨提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

        //修改用户基础信息
         _this.username = window.sessionStorage.getItem("username");
        axios.post("http://localhost:8181/leave/examLeave/"+_this.username, _this.ruleForm)
        .then(function (resp) {

            switch(resp.data){
            case "noResponse":
                _this.$message.error("未给出审核结果！");
            break;

            case "repeat":
                setTimeout(()=>{window.location.reload();},500)//延迟时间：0.5秒
                _this.$message.error("该员工已经重新提交新的申请表！审核失败！");
            break;

            case "false":
                 setTimeout(()=>{window.location.reload();},500)//延迟时间：0.5秒
                _this.$message.error("未知错误");
            break;

            case "empty":
                    setTimeout(()=>{window.location.reload();},500)//延迟时间：0.5秒
              _this.$message.error("该申请表信息不存在！");
            break;

            case "sendDept":
                    setTimeout(()=>{window.location.reload();},500)//延迟时间：0.5秒
                _this.$message({message: "已发送至副经理审核！",type: "success",});    
            break;

            case "sendoffice":
                    setTimeout(()=>{window.location.reload();},500)//延迟时间：0.5秒
                _this.$message({message: "已发送至总经理审核！",type: "success",});    
            break;

            case "error":
              setTimeout(()=>{window.location.reload();},500)//延迟时间：0.5秒
             _this.$message.error("审核未通过！已发回员工！"); 
            break;  
            
            case "success":
              setTimeout(()=>{window.location.reload();},500)//延迟时间：0.5秒
              _this.$message({message: "审核通过",type: "success",});    
              
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
    Hide(choice){
      switch(choice){
        case "部门经理":
          this.selectAuditTeacher =true;
          break;
        case "副经理":
          this.selectAuditLeader = true;
          break;   
        case "总经理":
          this.selectAuditMaster = true;
          break;
      }
    }
  },
  created(){
        const _this = this;
        _this.findExamLeave();
        var choice = window.sessionStorage.getItem("choice");
        _this.Hide(choice);
      if(choice === "部门经理"||choice === "副经理"||choice === "总经理"){
        
      }else{
        this.$router.push({name: "MasterPage"});
      }
  },
}
</script>

<style>

</style>