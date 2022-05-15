<template>
<body>
    <el-card>
        <el-form>

        <div style="text-align:left">

            <el-form-item  label="按组号显示" prop="classes" >

            <el-select v-model="item" placeholder="请选择"  @change="showByChoice()" v-show="selectClass">
            <el-option v-for="item in classes.length" :key="classes[item-1]" :label="classes[item-1]" :value="classes[item-1]"></el-option>
             </el-select>

            <el-input style="width:100px; margin-left: 10px" v-model="item" v-show="selectDept"
            size="small" @keyup.enter.native="item" @blur="showByChoice"
            ></el-input>
         
        </el-form-item>
        </div>

      </el-form>

        <div v-show="selectAttend">
        <el-row>
            <el-col :span="12" :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                <div id="ChatrAttend" :style="{ width: '400px', height: '400px' }"></div>
            </el-col>

            <el-col :span="12" :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
               <el-form label-width="100px" :label-position="labelPosition" >

                   <div style="text-align:left;font-size:20px; margin:5px;;padding:5px ;">
                        <h2>考勤成绩分布</h2>
                   </div>

                 <el-form-item label="不及格人数(0分-60分)：" label-width="200px">
                     <div style="text-align:left;">
                        <el-tag  effect="dark" type="danger" style=" margin-left: 10px"  >{{this.score[0]}}人</el-tag>
                     </div>
                </el-form-item>

               <el-form-item label="及格人数(60分-75分)：" label-width="200px">
                    <div style="text-align:left;">
                        <el-tag  effect="dark" type="primary" style=" margin-left: 10px"  >{{this.score[1]}}人</el-tag>
                     </div>
                </el-form-item>

               <el-form-item label="良好人数(75分-90分)：" label-width="200px">
                    <div style="text-align:left;">
                        <el-tag  effect="dark" type="primary" style=" margin-left: 10px"  >{{this.score[2]}}人</el-tag>
                     </div>
                </el-form-item>

                <el-form-item label="优秀人数(90分-100分)：" label-width="200px">
                    <div style="text-align:left;">
                        <el-tag  effect="dark" type="success" style=" margin-left: 10px"  >{{this.score[3]}}人</el-tag>
                     </div>
                </el-form-item>

          </el-form>

            </el-col>

        </el-row>
        </div>
    </el-card>
</body>
</template>

<script>
export default {
    watch: {
        option: {
            handler(newVal, oldVal) {
                    if (this.myChart) {
                    if (newVal) {
                        this.myChart.setOption(newVal);
                    } else {
                        this.myChart.setOption(oldVal);
                    }
                    } else {
                    this.ChatrAttend();
                    }
                },
            deep: true,
        },
    },
   data(){
       return{
           labelPosition:'right',
            selectClass:true,
            selectDept:false,
            ruleForm:{
                username:""
            },
            classes:[],
            item:"",
            score:[],
            selectAttend:false,
            option: {
                title: {
                text: "考勤人数统计",
                left: "center",
                },
                tooltip: {
                trigger: "item",
                },
                legend: {
                orient: "vertical",
                left: "left",
                },
                series: [
                {
                    type: "pie",
                    radius: "50%",
                    data: [
                    {
                        value: "",
                        name: "",
                    }
                    ],
                    emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: "rgba(0, 0, 0, 0.5)",
                    },
                    },
                },
                ],
            },
       }
    },
    methods:{
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
        findChatrAttend(){
            const _this = this;
            axios.get('http://localhost:8181/attend/showChatrAttend/'+_this.item).then(function(resp){
                _this.option.series[0].data = resp.data;
            }); 
        },
        findChatrScore(){
            const _this = this;
            axios.get('http://localhost:8181/attend/showChatrScore/'+_this.item).then(function(resp){
                _this.score = resp.data;
            }); 
        },
        showByChoice(){
            const _this = this;
            var choice =  window.sessionStorage.getItem("choice");
            if(choice === "部门经理"){
                    this.findAll();
            }else{
                _this.selectClass =false;
                _this.selectDept = true;
            }

            if(this.item === ""){
                this.selectAttend = false;
            }else{
                this.findChatrAttend();
                this.findChatrScore();
                this.selectAttend = true;
            }
        },

        ChatrAttend() {
            let chartDom = document.getElementById("ChatrAttend");
            let myChart = this.$echarts.init(chartDom);
            myChart.setOption(this.option, true);
        }, 
    },
    created(){
        this.showByChoice();
    }
}
</script>

<style>

</style>