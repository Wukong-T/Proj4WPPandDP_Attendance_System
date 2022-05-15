<template>
  <body>

    <div>
      <!-- 发送申请表弹出框 -->
      <el-dialog
        :visible.sync="sendLeaveDataDialog"
        width="60%"
        style="align:center"
        :close-on-click-modal="false"
        @close="resetForm('LeaveForn')"
        >
        <el-row>
          <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                   <div style="text-align:center;" ><!--border:1px solid #000 -->
          <el-form :inline="true" :rules="rules" :model="LeaveForn" ref="LeaveForn" class="demo-form-inline" >
            <el-row > 
                <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" >
               <h2 style="font-size:20px; margin:12px;text-align: center; margin-bottom:20px;">
                    请假申请表</h2>

              </el-col>
                   <el-col :span="24" :xs="12" :sm="12" :md="24" :lg="24" :xl="24" >
                  <el-form-item label="姓名 " prop="realname"    label-width="50px">
                    <el-tag  effect="plain"  type="info"  style="width:250px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{LeaveForn.realname}}</i> </el-tag>

                </el-form-item>
                <el-form-item label="工号 " prop="username"  label-width="50px">
                   <el-tag  effect="plain"  type="info"  style="width:250px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{LeaveForn.username}}</i> </el-tag>
                </el-form-item>
              </el-col>
               <el-col :span="24" :xs="12" :sm="12" :md="24" :lg="24" :xl="24">
                <el-form-item label="组号 " prop="classes"    label-width="50px">
                 <el-tag  effect="plain"  type="info"  style="width:250px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{LeaveForn.classes}}</i> </el-tag>
              </el-form-item>
              <el-form-item label="部门 " prop="dept"  label-width="50px">
                  <el-tag  effect="plain"  type="info"  style="width:250px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{LeaveForn.dept}}</i> </el-tag>
              </el-form-item>
            </el-col>
            <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form-item label="请假时间" prop="duration"  label-width="100px">
                    <div style="text-align: left">
                    <el-radio-group v-model="LeaveForn.duration">
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
                      size="small"
                      style="width:150px ;"
                      v-model.trim="LeaveForn.startTime"
                      type="date"
                      placeholder="选择日期">
                    </el-date-picker>
                  </div>
              </el-form-item>
              <el-form-item label="结束时间 " prop="endTime"  label-width="100px">
                  <div class="block">
                    <el-date-picker
                      size="small"
                      style="width:150px ;"
                      v-model.trim="LeaveForn.endTime"
                      type="date"
                      placeholder="选择日期">
                    </el-date-picker>
                  </div>
              </el-form-item>
            </el-col>

            <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <div style="text-align:center">
              <el-form-item label="请假原因 " prop="reason">
                    <el-input
                    style="width:500px ;"
                      type="textarea"
                      placeholder="请输入请假原因"
                      v-model.trim="LeaveForn.reason"
                      maxlength="1000"
                      show-word-limit
                    >
                    </el-input>
                </el-form-item>
                </div>
            </el-col>

             <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <el-form-item>
              <div style="text-align:left;font-size:12px; margin:24px;margin-bottom:20px;">
                <el-tag  effect="plain" type="danger" style="height:100% ;text-align: left;" > 
                 <i style="text-align: left;font-size:12px;color:#000;">
                  （1）请假原则上每学期不能超过四周，必须由员工本人提前申请。<br>
                  （2）请假时间在一天内或者一天以上三天以内的，由部门批准；<br>
                  （3）请假时间在三天以上一周以内的部门经理审核后提交给副经理批准;<br>
                  （4）请假时间在一周以上四周以内的在部门经理和副经理审核后交由总经理批准。</i> </el-tag>
              </div>
              </el-form-item>
              </el-col>

              <el-col :span="24" :xs="12" :sm="12" :md="24" :lg="24" :xl="24">
                <el-form-item label="审批人 " prop="checkname"  v-if="select=false"  label-width="100px">
                  <el-input
                    disabled
                    placeholder="请输入审批人"
                    v-model.trim="LeaveForn.checkname"
                  ></el-input>
              </el-form-item>

              <el-form-item label=" " prop="state" v-if="select=false"  label-width="50px">
                    <div style="text-align: left">
                    <el-radio-group v-model="LeaveForn.state" disabled  size="small" fill=green >
                         <el-radio-button label="审核通过"></el-radio-button>
                    </el-radio-group>
                    <el-radio-group v-model="LeaveForn.state" disabled  size="small" fill=red >
                         <el-radio-button  label="审核未通过"></el-radio-button>
                    </el-radio-group>
                    </div>
              </el-form-item>
            </el-col>
            <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                <el-form-item size="small" class="text-center subBtn">
                  <el-button type="primary" @click.prevent="sendLeave('LeaveForn')">发送申请</el-button>
                  <el-button round @click="resetLeaveForm('LeaveForn')"
                            >重置</el-button
                          >
                </el-form-item>
            </el-col>
         </el-row>
        </el-form>
        </div>
          </el-col>
        </el-row>

      </el-dialog>


        <!-- 修改 -->
        <el-dialog
        :visible.sync="updateLeaveDataDialog"
        width="60%"
        style="align:center"
        :close-on-click-modal="false"
          @close="resetForm('ruleForm')"
        >
        <el-tag  effect="plain"  type="info" style="width:100%;height:100%"> 
                 
        <el-row>
          <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
        <el-form :inline="true" :rules="rules" :model="ruleForm" ref="ruleForm" class="demo-form-inline" >
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
                    <el-radio-group v-model="ruleForm.duration">
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
                    <el-input
                    style="width:500px ;"
                      type="textarea"
                      placeholder="请输入请假原因"
                      v-model.trim="ruleForm.reason"
                      maxlength="1000"
                      show-word-limit
                    >
                    </el-input>
                </el-form-item>
                </div>
            </el-col>

              <el-col :span="24" :xs="12" :sm="12" :md="24" :lg="24" :xl="24">
                <el-form-item label="审批人 " prop="checkname"    label-width="100px">
                 <el-tag  effect="plain"  type="info"  style="width:250px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{ruleForm.checkname}}</i> </el-tag>
              </el-form-item>

              <el-form-item label=" " prop="state"  label-width="50px">
                    <div style="text-align: left">
                   <el-button type="primary" size="small"  @click.prevent="sendUpdateLeave('ruleForm')">确定发送</el-button>
                    </div>
              </el-form-item>
            </el-col>
         </el-row>
        </el-form>
          </el-col>
        </el-row>
        </el-tag>
      </el-dialog>

        <!-- 查看审批人意见 -->
        <el-dialog
        :visible.sync="showAuditDataDialog"
        width="60%"
        style="align:center"
        :close-on-click-modal="false"
          @close="resetForm('ruleForm')"
        >
        <el-row>
          <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
        <el-form :inline="true" :rules="rules" :model="ruleForm" ref="ruleForm" class="demo-form-inline" >
            <el-row > 

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

            <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" >
              <div style="text-align:center">
              <el-form-item label="部门经理审核意见 " prop="auditTeacher">
                  <el-tag  effect="plain"  type="info"  style="height:64px;width:500px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{ruleForm.auditTeacher}}</i> </el-tag>
                </el-form-item>
                </div>
            </el-col>

              <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" >
              <div style="text-align:center">
              <el-form-item label="副经理审核意见 " prop="auditLeader">
               <el-tag  effect="plain"  type="info"  style="height:64px;width:500px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{ruleForm.auditLeader}}</i> </el-tag>
                </el-form-item>
                </div>
            </el-col>

              <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <div style="text-align:center">
              <el-form-item label="总经理审核意见 " prop="auditMaster">
               <el-tag  effect="plain"  type="info"  style="height:64px;width:500px;text-align: left;" > 
                      <i style="text-align: left;font-size:12px;color:#000">{{ruleForm.auditMaster}}</i> </el-tag>
                </el-form-item>
                </div>
            </el-col>

         </el-row>
        </el-form>
          </el-col>
        </el-row>

      </el-dialog>
    </div>
    <el-card >

      <div style="text-align: left; ">
        <el-row><el-col>
        <el-form :inline="true"  class="demo-form-inline">

          <el-form-item>
            <div style="text-align: left; ">
                <el-tag effect="dark" type="info"  style="font-size:15px;">
                  <h2>当前请假表审核状态</h2>
                </el-tag>
            </div>
          </el-form-item>


          <el-form-item  >
                  <div style="text-align:left;">
        <el-button plain  type="success" icon="el-icon-zoom-in" @click="sendLeaveDialog()"
              >填写请假申请表</el-button
            >
        </div>
          </el-form-item>
   

        </el-form>
      </el-col></el-row>
      </div>

   <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
    <el-table class="mytable" :data="tableData" border >
      <!-- <el-table-column prop="frequency" label="序号" align="center" width="50px" ></el-table-column> -->
        <el-table-column prop="realname" align="center" width="100px" label="真实姓名"></el-table-column>
        <el-table-column prop="username"  align="center" width="100px" label="学号"></el-table-column>
        <el-table-column prop="duration"  align="center" width="100px" label="请假时长" ></el-table-column>
        <el-table-column prop="startTime" align="center" width="100px"  label="起始时间"
        :formatter="dataFormat"  ></el-table-column>
        <el-table-column prop="endTime" align="center" width="100px"  label="结束时间" 
        :formatter="dataFormat"></el-table-column>
        <el-table-column prop="reason" align="center" width="100px"  label="请假原因"></el-table-column>
        <el-table-column prop="checkname" align="center" width="100px"  label="审批人" ></el-table-column>
        <el-table-column prop="state" align="center" width="100px"  label="审核状态" >
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
            >重发申请表</el-button>

        <el-button
          style="margin-left: 10px;"
            icon="el-icon-edit"
            type="warning"
            size="small"
            @click="showAudit(scope.row)"
            >查看审批人意见</el-button>
        </template>
      </el-table-column>
    </el-table>

   </el-col>

    <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
      <div style="text-align: left; ">
        <el-row><el-col>
        <el-form :inline="true"  class="demo-form-inline">
            
          <el-form-item  >
          <div style="text-align:left;font-size:12px; margin:24px;margin-bottom:20px;">
            <el-tag  effect="plain" type="danger" style="height:100% ;text-align: left;" > 
          <i style="text-align: left;font-size:12px;color:#000;">
            1、当前的请假表正在审核阶段，则不允许再次提交申请；<br>
            2、如果在审批途中发现请假表有误，可重新发送申请表；<br>
            3、如果审核已经结束（审核通过/审核未通过），此申请表不可修改重新发送。</i> </el-tag>
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
      LeaveForn:{
        realname:"",
        username:"",
        classes:"",
        dept:"",
        duration:"",
        startTime:"",
        endTime:"",
        reason:"",
        checkname:"",
        state:"审核中",
      },
      dataForm:{},
      ruleForm:{},
      username:"",
      tableData:[],
      updateLeaveDataDialog:false,
      sendLeaveDataDialog:false,
      showAuditDataDialog:false,
      rules:{
      username: [
        { required: true, message: "请输入账号", trigger: "blur" },
        { min: 5, message: "长度不能少于5个字符", trigger: "blur" },
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
      classes : [{ required: true, message: "组号不能为空", trigger: "blur" }],
      dept: [{ required: true, message: "部门不能为空", trigger: "blur" }],
      duration: [{ required: true, message: "请假时间不能为空", trigger: "blur" }],
      reason: [{ required: true, message: "请假原因不能为空", trigger: "blur" }],
      checkname: [{ required: true, message: "审批人不能为空", trigger: "blur" }],
      startTime:[{ required: true, message: "开始时间不能为空", trigger: "blur" }],
      endTime:[{ required: true, message: "结束时间不能为空", trigger: "blur" }],
      }
    }
  },
  methods:{
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
      resetLeaveForm(formName){
        this.$refs[formName].resetFields();
         this.findLeave();
      },
      findLeave(){
          const _this = this;
        _this.LeaveForn.username = window.sessionStorage.getItem("username");
        axios.get('http://localhost:8181/data/findAllByUsername/'+_this.LeaveForn.username).then(function(resp){
            _this.dataForm = resp.data;
            _this.LeaveForn.realname = _this.dataForm.realname;
            _this.LeaveForn.classes = _this.dataForm.classes;
            _this.LeaveForn.dept = _this.dataForm.dept;

        axios.get('http://localhost:8181/leave/findCheckname/'+_this.dataForm.classes).then(function(resp){
                  if(resp.data == "error"){
                    _this.$message.error( _this.LeaveForn.classes+"班暂无辅导员管理！故暂不允许申请请假！");
                      setTimeout(()=>{window.location.reload();},500)//延迟时间：0.5秒
                  }else{
                      _this.LeaveForn.checkname= resp.data;
                  }
              }); 
        }) 
      },
      sendUpdateLeave(formName){
        const _this = this;
        this.$refs[formName].validate((valid) => {
        if (valid) {           
        this.$confirm('是否确定修改并发送？', '温馨提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          
        //修改用户基础信息
        axios.post("http://localhost:8181/leave/updateLeave", _this.ruleForm)
        .then(function (resp) {

            switch(resp.data){
            case "over":
                _this.$message.error("审核已经结束！申请表不可修改! 请重新申请！");
                 setTimeout(()=>{window.location.reload();},1000)//延迟时间：0.5秒
          
            break;
            case "empty":
                _this.$message.error("该申请表信息不存在！");
                 setTimeout(()=>{window.location.reload();},1000)//延迟时间：0.5秒
            break;


            case "success":
              _this.$message({message: "重新发送成功",type: "success",});    
              setTimeout(()=>{window.location.reload();},1000)//延迟时间：0.5秒
              
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
      showAudit(row){
        this.showAuditDataDialog = true;
         this.findById(row);
      },
      sendLeaveDialog(){
        this.sendLeaveDataDialog = true;
        this.findLeave();
      },
      updateLeave(row){
        this.updateLeaveDataDialog = true;
        this.findById(row);

      },
      findAllOwnLeave(){
       var _this=this;
      _this.username = window.sessionStorage.getItem("username");
      axios.get('http://localhost:8181/leave/ShowOwnLeave/'+_this.username).then(function(resp){
                _this.tableData = resp.data.content;
            }); 
      },
      findById(row){
       const _this = this;
      axios.get('http://localhost:8181/leave/findAllById/'+row.id ).then(function(resp){
          _this.ruleForm = resp.data     
        });
    },
      sendLeave(formName){
          const _this = this;
          this.$refs[formName].validate((valid) => {
            if (valid) {
                axios.post("http://localhost:8181/leave/sendLeave",_this.LeaveForn)
                .then(function (resp) {

                  switch(resp.data){
                    case "repeat":
                      _this.$message.error("申请表正在审核中!请勿重复申请！");
                      _this.$refs["LeaveForn"].resetFields();
                    break;

                    case "success":
                      _this.$refs[formName].resetFields();   
                      _this.$message({
                        message: "申请表发送成功！",
                        type: "success",
                      });
                      setTimeout(()=>{
                  //需要延迟的代码 :一定时间后延迟刷新界面（留取一定时间提示）
                    window.location.reload();},500)//延迟时间：1秒
                        break;  

                  }

                })
                .catch((failResponse) => {});
            } else {
              return false;
            }
          });
      },
  },
  created(){
       const _this = this;
      _this.findAllOwnLeave();
   var choice = window.sessionStorage.getItem("choice");
         if(choice === "员工"){
        
      }else{
        this.$router.push({name: "MasterPage"});
      }
  }
}
</script>

<style>

</style>