import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin from "./views/admin.vue"
import Welcome from "./views/admin/welcome.vue"
import Chapter from "./views/admin/chapter.vue"
import Section from "./views/admin/section.vue"
import Course from "./views/admin/course"
import Category from "./views/admin/category"
import Test from "./views/admin/test"
import Teacher from "./views/admin/teacher"
import CustomFile from "./views/admin/customFile"
import User from "./views/admin/user"
import Resource from "./views/admin/resource"

Vue.use(Router);

export default new Router({
    mode : "history",
    base: process.env.BASE_URL,
    routes: [{
        path: "*",
        redirect: "/login",
    },{
        path: "/login",
        component: Login
    },{
        path: "/",
        name: "admin",
        component: Admin,
        meta: {
            loginRequire: true
        },
        children: [{
            path: "welcome",
            name: "welcome",
            component: Welcome,
        },{
            path: "business/chapter",
            name: "business/chapter",
            component: Chapter,
        },{
            path: "business/section",
            name: "business/section",
            component: Section,
        },{
            path: "business/course",
            name: "business/course",
            component: Course
        },{
            path: "business/category",
            name: "business/category",
            component: Category
        },{
            path: "business/test",
            name: "business/test",
            component: Test
        },{
            path: "business/teacher",
            name: "business/teacher",
            component: Teacher
        },{
            path: "business/customFile",
            name: "business/customFile",
            component: CustomFile
        },{
            path: "system/user",
            name:"system/user",
            component: User
        },{
            path: "system/resource",
            name:"system/resource",
            component: Resource
        }]
    }]
})