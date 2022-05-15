<template>
 <body>

 <el-card style="margin:5px;" class="box-card">  
  <div> 

    <el-row>
      <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
    <el-form :inline="true" :label-position="labelPosition" :model="NoticeForm" ref="ruleForm" class="demo-form-inline" >
        <el-row > 

        <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" >
          <div style="text-align:left">

          <el-form-item label="公告栏" prop="content">
              <el-tag  effect="plain"  type="info"  style="height:64px;width:500px;text-align: left;" > 
                  <i style="text-align: left;font-size:12px;color:#000">{{NoticeForm.content}}</i> </el-tag>
            </el-form-item>
            <div>
              <el-link v-show="selectNotice"
              type="primary"
              @click.prevent="noticeMange()"
              style="padding:20px "> <i class="el-icon-s-management"></i>公告管理</el-link>
            </div>

            <div>
              <el-link v-show="selectStudnet"
              type="primary"
              @click.prevent="showAll()"
              style="padding:20px "> <i class="   el-icon-reading"></i>公告详情</el-link>
            </div>

        
            </div>
        </el-col>

      </el-row>
    </el-form>
      </el-col>
    </el-row>
  </div>
</el-card>

  <el-card style="margin:5px;" class="box-card">
    <el-row>
      <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">   
      
        <div style="text-align:left;border-radius: 4px" >
        <el-form :inline="true" :model="ruleForm" ref="ruleForm" class="demo-form-inline" >
        <div style="text-align:left;">
          <el-col>
            <el-form-item label="" prop="realname" label-width="50px">
              <el-tag  effect="dark"  type="success"  style="width:100px;text-align: left;" > 
                <i style="text-align: left;font-size:15px;">{{ruleForm.realname}}</i> </el-tag>
            </el-form-item>
            

            <el-form-item label="" prop="dept" label-width="50px" v-show="selectDept">
            <el-tag  effect="dark"  type="primary"  style="width:150px;text-align: left;" > 
              <i style="text-align: left;font-size:15px;">{{ruleForm.dept}}</i> </el-tag>
            </el-form-item>
      
          <el-form-item label=" " prop="choice"  v-show="selectChoice">
            <el-tag  effect="dark"  type="danger"  style="width:100px;text-align: left;" > 
              <i style="text-align: left;font-size:15px;">{{ruleForm.choice}}</i> </el-tag>
          </el-form-item>

          <el-form-item label="组号:" prop="classes"  v-show="selectStudnet">
            <el-tag  effect="dark"  type="warning"  style="width:100px;text-align: left;" > 
              <i style="text-align: left;font-size:12px;">{{ruleForm.classes}}</i> </el-tag>
          </el-form-item>

          <el-form-item label="所在组号:"   v-show="selectTeacher">
          <div style="text-align:left;;" >
          <el-tag  effect="dark"  :key="tag" v-for="tag in classes" style=" margin-left: 10px" :disable-transitions="false"  
          type="warning" ><i style="text-align: left;font-size:12px;">{{tag}}</i></el-tag>
            </div>
          </el-form-item>
          </el-col>

      </div>
      </el-form>
        </div>
      
      </el-col>
  </el-row>

</el-card>  

 <el-card style="margin:5px;" class="box-card" v-show="selectStudnet">  
   <el-row>

     <el-col :span="12" :xs="12" :sm="12" :md="12" :lg="12" :xl="12"> 
      <div id="ShowOwnattend" style="width: 600px;height: 200px;"> </div>
     </el-col>

      <el-col :span="12" :xs="12" :sm="12" :md="12" :lg="12" :xl="12"> 
        <div style="text-align: left; ">
        <el-row><el-col>
        <el-form :inline="true"  class="demo-form-inline">

          <el-form-item>
            <div style="text-align: left; ">
                  <h1  style="font-size:20px;color:#000;line-height:1.7 ">审核状态</h1>
            </div>
          </el-form-item>
        </el-form>
      </el-col></el-row>
      </div>
      <el-table class="mytable" :data="tableData" border >
      <!-- <el-table-column prop="frequency" label="序号" align="center" width="50px" ></el-table-column> -->
        <el-table-column prop="duration"  align="center"  label="请假时长" ></el-table-column>
        <el-table-column prop="startTime" align="center"  label="起始时间"
        :formatter="dataFormat"  ></el-table-column>
        <el-table-column prop="endTime" align="center"   label="结束时间" 
        :formatter="dataFormat"></el-table-column>
        <el-table-column prop="reason" align="center"  label="请假原因"></el-table-column>
        <el-table-column prop="checkname" align="center" label="审批人" ></el-table-column>
        <el-table-column prop="state" align="center"  label="审核状态" >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.state === '审核通过' ? 'success' : 'warning'
              &&scope.row.state === '审核未通过' ? 'danger' : 'warning'
              && scope.row.state === '审核中' ? 'info' : 'warning'"
              disable-transitions>{{scope.row.state}}</el-tag>
          </template>
        </el-table-column>
    </el-table>

     </el-col>   
      </el-row>
 

</el-card>
 </body>
    



</template>

<script>

export default {
  name: "Dashboard",
  mounted() {
    // this.echart('main');
    this.Histogram('ShowOwnattend');
  },

  data(){       
    return{
        charts: '',
        opinion:['早退','迟到','请假','旷工'],
        opinionData:[],

        classes:[],

      showNoticeDataDialog:false,
        labelPosition: 'top',
        selectStudnet:"",//学生可见
        selectTeacher:"",//所管班级
        selectDept:"",//院系
        selectChoice:"",//用户类型
        selectname:true,
        selectNotice:"",
        ruleForm: {
          realname:"",
          username: "",
          classes:"",
          depts:"",   
          choice:[],
        },
      tableData:[],
      NoticeForm:{},
      chartData:[],
      }
  },

  methods:{
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
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
    Hide(choice){
      switch(choice){
         case "员工":
        //  console.log("test")
            this.selectStudnet = true;
            this.selectDept= true;
            break;
          case "部门经理":
            this.selectTeacher = true;
            this.selectDept = true;
            this.selectNotice=true;
            break;
          case "总经理":
            this.selectChoice = true;
             this.selectNotice=true;
            // this.selectAdmin = true;
            break;
          case "副经理":
            this.selectDept = true;
             this.selectNotice=true;
            break;
        };
    },
    findAll(){  
      const _this = this;
      _this.ruleForm.username = window.sessionStorage.getItem("username");
      axios.get('http://localhost:8181/data/findAllByUsername/'+_this.ruleForm.username).then(function(resp){
          _this.ruleForm = resp.data
      // console.log(_this.value)
      axios.get('http://localhost:8181/leave/findClass/'+_this.ruleForm.realname).then(function(resp){
              _this.classes = resp.data;
        }); 
      });
    },
    findAllOwnLeave(username){
      var _this=this;
      axios.get('http://localhost:8181/leave/ShowOwnLeave/'+username).then(function(resp){
                _this.tableData = resp.data.content;
        }); 
      },
    showAll(){
      this.$router.push({ name: "NoticeShowAll" });
    },
    Histogram(id){
      var chartDom = document.getElementById(id);
      var myChart = echarts.init(chartDom);
      var option;

      var dataAxis = ['早退', '迟到', '请假', '旷工']
      var data = this.chartData
      var yMax = 500;
      var dataShadow = [];

      for (var i = 0; i < data.length; i++) {
          dataShadow.push(yMax);
      }

      option = {
          title: {
              text: '考勤情况',
              // subtext: 'Feature Sample: Gradient Color, Shadow, Click Zoom'
          },
          xAxis: {
              data: dataAxis,
              axisLabel: {
                  inside: true,
                  textStyle: {
                      color: '#fff'
                  }
              },
              axisTick: {
                  show: false
              },
              axisLine: {
                  show: false
              },
              z: 10
          },
          yAxis: {
              axisLine: {
                  show: false
              },
              axisTick: {
                  show: false
              },
              axisLabel: {
                  textStyle: {
                      color: '#999'
                  }
              }
          },
          dataZoom: [
              {
                  type: 'inside'
              }
          ],
          series: [
              {
                  type: 'bar',
                  showBackground: true,
                  itemStyle: {
                      color: new echarts.graphic.LinearGradient(
                          0, 0, 0, 1,
                          [
                              {offset: 0, color: '#83bff6'},
                              {offset: 0.5, color: '#188df0'},
                              {offset: 1, color: '#188df0'}
                          ]
                      )
                  },
                  emphasis: {
                      itemStyle: {
                          color: new echarts.graphic.LinearGradient(
                              0, 0, 0, 1,
                              [
                                  {offset: 0, color: '#2378f7'},
                                  {offset: 0.7, color: '#2378f7'},
                                  {offset: 1, color: '#83bff6'}
                              ]
                          )
                      }
                  },
                  data: data
              }
          ]
      };

      // Enable data zoom when user click bar.
      var zoomSize = 6;
      myChart.on('click', function (params) {
          // console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
          myChart.dispatchAction({
              type: 'dataZoom',
              startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
              endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
          });
      });

      option && myChart.setOption(option);
    
    },
    findNotice(){
      var _this=this;
      axios.get('http://localhost:8181/notice/showNotice/').then(function(resp){
            _this.NoticeForm = resp.data;

            if( _this.NoticeForm.content === null){
                _this.NoticeForm.content = "无"
                _this.selectname = false;
              }
            }); 

    },
    noticeMange(){
        this.$router.push({ name: "NoticeMange" });
    },
    findChartData(){
       var username = window.sessionStorage.getItem("username");
       const _this = this
      axios.get('http://localhost:8181/attend/showChatrStudent/'+username).then(function(resp){
              _this.chartData = resp.data;
        }); 
    }
  },
  created(){
      this.ruleForm.username = window.sessionStorage.getItem("username");
      this.ruleForm.choice = window.sessionStorage.getItem("choice");
        this.findNotice();
      if( this.ruleForm.choice === "人事管理员"){
        this.ruleForm.realname = "人事管理员";
         this.selectChoice = false;
      }else if(this.ruleForm.choice === "员工"){
          this.findAll();
          this.Hide(this.ruleForm.choice);
          this.findAllOwnLeave(this.ruleForm.username);
          this.findChartData();
      }
      else{
          this.findAll();
          this.Hide(this.ruleForm.choice);
      }

  },
  watch: {
	  //监听的变量名
      chartData(val) {
        this.chartData = val
        this.Histogram('ShowOwnattend');
      },
    }

}
</script>

<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  }
  .item {
  margin-top: 10px;
  margin-right: 40px;
  }
</style>