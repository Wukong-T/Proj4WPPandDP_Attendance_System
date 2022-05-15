<template>
  <div>
    
    <el-card>
      <el-col :span="24" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">   
        <!-- <h2 style="font-size:15px; margin:24px;text-align: left; margin-bottom:20px;">以往申请记录</h2> -->
        <div style="text-align: left; ">
            <el-tag effect="dark" type="info"  style="font-size:15px; margin:24px;margin-bottom:20px;">
              <h2>以往申请记录</h2>
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
    </el-card>

  </div>
</template>

<script>
export default {
  data(){
    return{
    
      currentPage: 1,
      total: null,
      pageSize: 5,
      currentPageData: [] ,//当前页显示内容
      
      username:"",
      tableData:[],
    }
  },
  methods:{
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
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
    findAllOldLeave(){
      var _this=this;
      _this.username = window.sessionStorage.getItem("username");
        axios.get('http://localhost:8181/leave/ShowOldLeave/'+_this.currentPage+'/'+_this.pageSize+'/'+_this.username).then(function(resp){
          _this.tableData = resp.data.content;
          }); 
    },
            //删除
    del(row) {
      console.log(row.id)
      this.$confirm("此操作将永久删除该用户信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 数据库...
          const _this = this;
          axios
            .delete("http://localhost:8181/leave/deleteLeave/" + row.id)
            .then(function (resp) {
              _this.$alert(" “ " + row.realname + " ” 的基本信息已删除！", "消息", {
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
  },
  created(){
       const _this = this;
      _this.findAllOldLeave();

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