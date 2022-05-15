<template>
 <el-container  class="mycontainer" style="height: 100%;">
  
     <el-header  class="mycontainer">
        <el-row :gutter="24">
        <el-col :span="4"  :xs="4" :sm="4" :md="4" :lg="4" :xl="4"
        style="text-align:left;font-size: 20px; padding:20px ; " 
          ><div class="grid-content bg-purple" style="text-align: left;">
            <h2 style="color:#FFFF33" >香蕉考勤管理系统</h2>
            
          </div>
          
        </el-col>
      
        <el-col :span="20" :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
         <div class="grid-content bg-purple" style="text-align: right;font-size:15px" >

    
          <el-tag effect="dark" v-show="selectAdmin" type="danger" > <i style="font-size:14px"> 管理员 </i></el-tag>

          

           <el-tag effect="dark" v-show="selectNoAdmin" type="primary" > 
             <i style="font-size:14px"> {{this.infomation.choice}}</i></el-tag>


              <i v-show="selectNoAdmin"
              type="primary"
              style="padding:20px;color:#fff">{{this.infomation.realname}}</i>

            <el-link
              type="primary"
              @click.prevent="handleSignout()"
              style="padding:20px "> <i class="el-icon-switch-button"></i>退出登录</el-link>

          </div> 
        </el-col>
      </el-row>
      
     </el-header>
     
  <el-container  class="mycontainer" style="height: 100%;">

        <el-menu default-active="1-4-1" 
            class="el-menu-vertical-demo "
            style="height: 100%;" 
            :collapse="isCollapse"
            :collapse-transition="false"

            :unique-opened="true"
            :router="true"
            background-color="#000000 "
            text-color="#fff"
            active-text-color="#FFFF00"
            
            >
      
            <el-menu-item index="MasterPage" >
                <i class="el-icon-house"></i>
                <span slot="title">首页</span>
            </el-menu-item>


            <el-submenu index="DataManage" v-show="selectmange" >
                <template slot="title">
                    <i class="el-icon-first-aid-kit"></i>
                    <span slot="title">信息管理</span>
            </template>
    
            <el-menu-item-group>   
                <el-menu-item index="studentManage">员工信息管理</el-menu-item>
                <el-menu-item index="TeacherManage">部门经理信息管理</el-menu-item>
                <el-menu-item index="LeaderManage">副经理信息管理</el-menu-item>
              
            </el-menu-item-group>
            </el-submenu>

           <el-submenu index="LeaveMange" v-show="selectNoAdmin">
                <template slot="title">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">请假管理</span>
            </template>
    
            <el-menu-item-group>
                <el-menu-item index="LeaveMange" v-show="SelectStaff">请假审核</el-menu-item>    
                <el-menu-item index="SendLeave"  v-show="SelectStudnet">请假申请</el-menu-item>
                <el-menu-item index="ShowLeave" v-show="SelectStaff">以往审核记录</el-menu-item>    
                <el-menu-item index="ShowOldLeave"  v-show="SelectStudnet">以往申请记录</el-menu-item>         
            </el-menu-item-group>
            </el-submenu>

            <el-submenu index="AttendManage"  v-show="selectNoAdmin">
                <template slot="title">
                    <i class="el-icon-s-data"></i>
                    <span slot="title">考勤管理</span>
            </template>
    
            <el-menu-item-group>
              <!-- <el-menu-item index="AttendStudnet" v-show="SelectStudnet" >个人考勤信息</el-menu-item> -->
              <el-menu-item index="AttendStudnet" v-show="SelectStudnet" >个人考勤信息</el-menu-item>
              <el-menu-item index="ShowAttend" v-show="SelectStaff">员工考勤管理</el-menu-item>
              <el-menu-item index="AttendCensus" v-show="SelectStaff">考勤信息统计</el-menu-item>
            </el-menu-item-group>
            </el-submenu>

            <el-submenu index="NoticeManage" v-show="selectNoAdmin">
                <template slot="title">
                    <i class="el-icon-files"></i>
                    <span slot="title">公告管理</span>
            </template>
    
            <el-menu-item-group>   
               <el-menu-item index="NoticeMange"  v-show="SelectStaff" >公告信息管理</el-menu-item>
              <el-menu-item index="NoticeShowAll"  v-show="SelectStudnet">公告信息详情</el-menu-item>
            </el-menu-item-group>
            </el-submenu>

            <el-submenu index="ShowUserAll"  v-show="selectAdmin">
                <template slot="title">
                    <i class="el-icon-help"></i>
                    <span slot="title">信息总览</span>
            </template>
    
            <el-menu-item-group>
              
              <el-menu-item index="ShowData"  >总览用户信息</el-menu-item>
              <el-menu-item index="ShowAccount" >总览账户信息</el-menu-item>
              <el-menu-item index="NoticeShowAll">公告信息总览</el-menu-item>

            </el-menu-item-group>
            </el-submenu>



            <el-submenu index="PersonalManagement" v-show="selectNoAdmin">
                <template slot="title">
                    <i class="el-icon-setting"></i>
                    <span slot="title">个人设置</span>
            </template>
    
            <el-menu-item-group>
                <el-menu-item  index="PersonalCenter">个人中心</el-menu-item>
                <el-menu-item  index="SecurityCenter">安全中心</el-menu-item> 
            </el-menu-item-group>
            </el-submenu>

        </el-menu>

    <el-container  class="mycontainer" style="height: 100%;"> 

      <el-header class="myheader" style="height:30px; background-color:#fff">
        <el-row>
            <el-col :span="20" :xs="2" :sm="4" :md="20" :lg="20" :xl="20">
          <div  style="text-align: left;">
            <el-link  @click="foldCollapse" style="font-size:25px" icon="el-icon-s-fold" v-show="foldShow"></el-link>
            <el-link  @click="unfoldCollapse"  icon="el-icon-s-unfold" style="font-size:25px;margin-left:0px" v-show="unfoldShow"></el-link>   
            <el-link  @click="refresh()"  icon="el-icon-refresh" style="font-size:25px;margin-left:5px" ></el-link>  
          </div>
        </el-col>
          <el-col :span="4" :xs="22" :sm="20" :md="4" :lg="4" :xl="4">
        <div style="text-align: right;">
          <el-breadcrumb class="breadcrumb-container" separator="/"  style="font-size:15px; height:100%;padding:10px " >
            <el-breadcrumb-item v-for="item in levelList" :key="item.path" :to="item.path">{{item.meta.title}}</el-breadcrumb-item>
          </el-breadcrumb>

      </div>
        </el-col>

      </el-row>
      </el-header>

    <el-main class="mymain" style="height:100%;">
        <router-view>
        </router-view>
    </el-main>
    </el-container>

</el-container>

  </el-container>
</template>

<script>
export default {
 data() {
      return {

        labelPosition:"left",

        //菜单折叠
        isCollapse: false,
        foldShow:true,
        unfoldShow:false,
      
        selectAdmin:false, //仅管理员可见
        SelectStudnet:false,//仅学生可见
        selectNoAdmin:true,//仅管理员不可见
        selectMaster:false,//仅教务处可见
        SelectStaff:false,//仅教职工可见
        selectmange:false,
        breadList:[],//面包屑

        //传入的数据
        loginuser: "",
        loginchoice: "",
  
        data:[],
        ruleForm: {
          username: "",
          realname: "",
          email: "",
          choice:[]
        },
        infomation:{},
      };
    },
    watch: {
    $route() {
        this.getBreadcrumb()
        }
    },
    created(){
        this.getBreadcrumb();

         const _this = this;
        _this.ruleForm.username = window.sessionStorage.getItem("username");
        _this.ruleForm.choice = window.sessionStorage.getItem("choice");
        axios.get('http://localhost:8181/user/findAllByUsername/'+_this.ruleForm.username).then(function(resp){
            _this.ruleForm = resp.data});
        axios.get('http://localhost:8181/data/findAllByUsername/'+_this.ruleForm.username).then(function(resp){
            _this.infomation = resp.data});
            
        switch(_this.ruleForm.choice){
          case "人事管理员":
           _this.selectNoAdmin = false;
           _this.selectAdmin = true;
           _this.selectmange = true;
           
          break;
          case "员工":
             _this.SelectStudnet = true;
          break;
          case "总经理":
             _this.SelectStaff = true;
            _this.selectMaster = true;
            _this.selectmange = true;
          break;
          
          default:
             _this.SelectStaff = true;
            break;
        }

        _this.infomation.realname = _this.ruleForm.username;

    },
    methods: {
      //刷新
      refresh(){
        window.location.reload();
      },
      //折叠
      foldCollapse(){
          this.isCollapse = !this.isCollapse;
          this.foldShow = false;
          this.unfoldShow = true;
      },
      //展开
      unfoldCollapse(){
          this.isCollapse = !this.isCollapse;
          this.unfoldShow = false;
          this.foldShow = true;
      },
      isCollapsede() {
        return this.$store.state.menuStatus.isCollapsed
      },

      //退出登录
      handleSignout(){
        this.$confirm('确定要退出登录吗?', '温馨提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          
          window.sessionStorage.clear();
           this.$router.push({ name: "login" });
          this.$message({
            type: 'success',
            message: '退出登录!'
          });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退出'
          });          
        });
      },
      //面包屑
      getBreadcrumb() {
      //$route.matched一个数组 包含当前路由的所有嵌套路径片段的路由记录
      let matched = this.$route.matched.filter(item => item.name)
      
      const first = matched[0];
      
    
      if (first && first.name.trim().toLocaleLowerCase() !== '首页'.toLocaleLowerCase()) {
          // matched = [{ path: '/MasterPage', meta: { title: '首页' }}].concat(matched)
          matched = [].concat(matched)
      }
      this.levelList = matched
      }
    },
}
</script>


<style>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
  .el-dropdown-link {
  cursor: pointer;
  color: #33FF66;
}

 .el-icon-arrow-down {
  font-size: 12px;
}
</style>