import Vue from 'vue'
import VueRouter from 'vue-router'

// 账户管理
import Login from '@/components/User/login.vue'
import Register from '../components/User/register.vue'
import FindPassword from '../components/User/findPassword.vue'


// 母版
import Motherboadr from '../components/MainMenu/Motherboadr.vue'

//首页
import MasterPage from '../components/MainMenu/MasterPage.vue'

//公告管理
import NoticeMange from '../components/NoticeManage/NoticeMange.vue'
import NoticeShowAll from '../components/NoticeManage/NoticeShowAll.vue'


//请假管理
import SendLeave from '../components/LeaveMange/SendLeave.vue'
import LeaveMange from '../components/LeaveMange/LeaveMange.vue'
import ShowOldLeave from '../components/LeaveMange/ShowOldLeave.vue'
import ShowLeave from '../components/LeaveMange/ShowLeave.vue'

//考勤管理
import AttendStudnet from '../components/AttendManage/AttendStudnet.vue'
import ShowAttend from '../components/AttendManage/ShowAttend.vue'
import AttendCensus from '../components/AttendManage/AttendCensus.vue'

//用户信息总览
import ShowAccount from '../components/ShowUserAll/ShowAccount.vue'
import ShowData from '../components/ShowUserAll/ShowData.vue'

//用户信息管理
import TeacherManage from '../components/DataManage/TeacherManage.vue'
import LeaderManage from '../components/DataManage/LeaderManage.vue'
import studentManage from '../components/DataManage/studentManage.vue'


//个人信息管理
import PersonalCenter from '../components/PersonalManagement/PersonalCenter.vue'
import SecurityCenter from '../components/PersonalManagement/SecurityCenter.vue'

Vue.use(VueRouter)

const routes = [
    // 登录页面：
    {name: 'login',path: '/login',component: Login},

    // 注册页面：
    {name: 'register', path: '/register',component: Register},      
    
    // 找回密码：
    {name: 'findPassword',path: '/findPassword',component: FindPassword},   

    // 系统界面：
    {path: '/', name: 'Motherboadr',redirect: 'login',component: Motherboadr,
        meta: {  requireAuth: true  }, // 配置此条，进入页面前判断是否需要登陆 
        children: [
            //首页
            { name: 'MasterPage', path: 'MasterPage', component: MasterPage,meta:{title:"首页"},},

            //公告管理
            { name: 'NoticeMange', path: 'NoticeMange', component: NoticeMange,meta:{title:"公告信息管理"},},
            { name: 'NoticeShowAll', path: 'NoticeShowAll', component: NoticeShowAll,meta:{title:"公告信息详情"},},

            //基本信息管理
            { name: 'studentManage', path: 'studentManage', component: studentManage, meta:{title:"学生信息管理"} },
            { name: 'TeacherManage', path: 'TeacherManage', component: TeacherManage, meta:{title:"辅导员信息管理"} },
            { name: 'LeaderManage', path: 'LeaderManage', component: LeaderManage, meta:{title:"院领导信息管理"} },
  
            //请假管理 
            { name: 'SendLeave', path: 'SendLeave', component: SendLeave, meta:{title:"请假申请"} },
            { name: 'ShowOldLeave', path: 'ShowOldLeave', component: ShowOldLeave, meta:{title:"以往申请记录"} },
            { name: 'LeaveMange', path: 'LeaveMange', component: LeaveMange, meta:{title:"请假审核"} },
            { name: 'ShowLeave', path: 'ShowLeave', component: ShowLeave, meta:{title:"以往审核记录"} },
            
            //考勤管理
            { name: 'AttendStudnet', path: 'AttendStudnet', component: AttendStudnet, meta:{title:"个人考勤信息"} },
            { name: 'ShowAttend', path: 'ShowAttend', component: ShowAttend, meta:{title:"学生考勤管理"} },
            { name: 'AttendCensus', path: 'AttendCensus', component: AttendCensus, meta:{title:"考勤信息统计"} },

            //用户信息总览
            { name: 'ShowData', path: 'ShowData', component: ShowData, meta:{title:"总览用户信息"} },
            { name: 'ShowAccount', path: 'ShowAccount', component: ShowAccount, meta:{title:"总览账户信息"} },
            
            //个人设置   
            { name: 'SecurityCenter', path: 'SecurityCenter', component: SecurityCenter, meta:{title:"安全中心"} },
            { name: 'PersonalCenter', path: 'PersonalCenter', component: PersonalCenter, meta:{title:"个人中心"} },
        ]
    },
]

const router = new VueRouter({
    routes
})

export default router