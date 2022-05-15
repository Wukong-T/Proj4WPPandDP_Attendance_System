<template>
<body>

            <!-- 查看学生反馈 -->
        <el-dialog
        :visible.sync="showProposeDataDialog"
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
              <el-form-item label="提出疑义 " prop="propose">
                <el-input
                    style="width:500px ;"
                      type="textarea"
                      placeholder="请输入疑义"
                      v-model.trim="ruleForm.propose"
                      maxlength="1000"
                      show-word-limit
                    >
                    </el-input>
                </el-form-item>
                </div>
             </el-col>
            <el-form-item>

            <el-row :span="12" :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
            <el-col>
            <el-button
                type="primary"
                size="small"
                @click.prevent="sendPropose('insertForm')"
            >确认发送</el-button>
            </el-col></el-row>
        </el-form-item>
         </el-row>
        </el-form>
          </el-col>
        </el-row>

      </el-dialog>

     <el-card style="margin:5px;" class="box-card">
        <div>
        <el-alert v-show="dialogVisible"
            title="考勤预警"
            type="error"
            description="考勤成绩低于60分！"
            show-icon>
        </el-alert>
        </div>
    <el-table class="mytable" :data="tableData" border >
    <el-table-column prop="realname" label="真实姓名" align="center" width="100px"></el-table-column>
    <el-table-column prop="username" label="工号" align="center" width="100px"></el-table-column>
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
          <el-button size="small" type="warning" style="margin-left: 10px;"  icon="el-icon-edit" @click="edit(scope.row)"
            >提出疑义</el-button>
        </template>
      </el-table-column>
    </el-table>
    </el-card>

    <el-card style="margin:5px;" class="box-card">
        <div id="ShowOwnattend" style="width: 600px;height: 300px;"> </div>
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
        chartData:[],
        tableData:[],
        ruleForm:{
            propose:""
        },
        dialogVisible:false,
        showProposeDataDialog:false,
        rules:{
            propose:[  { required: true, message: "提出的疑义不能为空" ,trigger: "blur" }]
        }
       }
    },
    methods:{
        //重置
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        findTable(){  
            const _this = this;
            var username = window.sessionStorage.getItem("username");
            axios.get('http://localhost:8181/attend/findAllOwn/'+username).then(function(resp){
                _this.tableData = resp.data.content;

            });
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
    //编辑（提交）按钮
    sendPropose(formName) {
      const _this = this;      
        this.$confirm('是否确定修改？', '温馨提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
        //修改用户基础信息
        axios.post("http://localhost:8181/attend/updateAttend", _this.ruleForm)
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
        findAll(){
            const _this = this;
            var username = window.sessionStorage.getItem("username");
            axios.get('http://localhost:8181/attend/findAllByUsername/'+username).then(function(resp){
                _this.ruleForm = resp.data
             
                if(_this.ruleForm.score < 60 ){
                    //    console.log(_this.ruleForm.score)
                    // alert("注意！你的考勤为"+_this.ruleForm.score)
                    _this.dialogVisible = true;
                }else{
                   _this.dialogVisible = false;
                }
            });
        },
        findChartData(){
        var username = window.sessionStorage.getItem("username");
        const _this = this
        axios.get('http://localhost:8181/attend/showChatrStudent/'+username).then(function(resp){
                _this.chartData = resp.data;
            }); 
        },
        edit(row){
            this.showProposeDataDialog=true;
             const _this = this;
            var username = window.sessionStorage.getItem("username");
            axios.get('http://localhost:8181/attend/findAllByUsername/'+username).then(function(resp){
                _this.ruleForm = resp.data});
                
        }
    },
    created(){
        this.findAll()
        this.findTable();
        this.findChartData();
    },
    watch: {
	  //监听的变量名
      chartData(val) {
        this.chartData = val
        this.Histogram('ShowOwnattend');
      },
    },
}
</script>

<style>

</style>