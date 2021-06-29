import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path: "/notice",
        name: "Notice",
        component: () => import("../views/Notice.vue"),
    },
    {
        path: "/notice/:id",
        name: "NoticeDetail",
        component: () => import("../views/NoticeDetail.vue"),
    },
    {
        path: "/event",
        name: "Event",
        component: () => import("../views/Event.vue"),
    },
    {
        path: "/event/:id",
        name: "EventDetail",
        component: () => import("../views/EventDetail.vue"),
    },
    {
        path: "/league",
        name: "League",
        component: () => import("../views/League.vue"),
    },
    {
        path: "/result",
        name: "Result",
        component: () => import("../views/Result.vue"),
    },
    {
        path: "/timetrial",
        alias: "/tt",
        name: "TimeTrial",
        component: () => import("../views/TimeTrial.vue"),
    },
    {
        path: "/faq",
        name: "Faq",
        component: () => import("../views/Faq.vue"),
    },
    {
        path: "/about",
        name: "About",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import(/* webpackChunkName: "about" */ "../views/About.vue"),
    },
    { path: "*", component: () => import("../views/PageNotFound.vue") },
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
    scrollBehavior(to, from, savedPosition) {
        return { x: 0, y: 0 };
    },
});

export default router;
