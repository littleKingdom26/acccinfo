<template>
    <div class="headerWrap" ref="headerWrap">
        <div class="header">
            <b-button variant="link" class="logo" to="/"></b-button>

            <div class="menus Staatliches">
                <b-button
                    variant="link"
                    v-for="(menu, menuIdx) in menus"
                    :key="menuIdx"
                    :to="menu.to"
                    :class="{
                        active:
                            menu.to == $router.currentRoute.path ||
                            (menu.to.length > 1 &&
                                $router.currentRoute.path.indexOf(menu.to) !=
                                    -1),
                    }"
                    :disabled="menu.disabled"
                >
                    {{ menu.title }}
                </b-button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            menus: [
                { title: "HOME", to: "/" },
                { title: "NOTICE", to: "/notice" },
                { disabled: true, title: "LEAGUE", to: "/league" },
                { disabled: true, title: "RESULT", to: "/result" },
                { disabled: true, title: "TIME TRIAL", to: "/timetrial" },
                { disabled: true, title: "EVENT", to: "/event" },
                { disabled: true, title: "GALLERY", to: "/gallery" },
                { disabled: true, title: "REGISTER", to: "/register" },
                { disabled: true, title: "FAQ", to: "/faq" },
            ],
        };
    },
    mounted() {
        let $ = this.$jquery;
        $(window).on("scroll", () => {
            $(this.$refs.headerWrap).css({
                backgroundColor: `rgba(0,0,0, ${$(window).scrollTop() / 60})`,
            });
        });
    },
};
</script>

<style scoped>
* >>> .yellow {
    color: var(--yellow);
}

.logo {
    width: 220px;
    height: 52px;
    background-image: url("/vue_assets/logo/final_lap_logo.png");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    display: inline-block;
    vertical-align: top;
}
.headerWrap {
    width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    z-index: 100;
    background-color: rgba(0, 0, 0, 0);
    transform: all 1s;
}
.headerWrap.active {
    background-color: rgba(0, 0, 0, 1);
}
.header {
    max-width: 1140px;
    margin: 0 auto;
    margin-top: 0.5rem;
}
.menus {
    max-width: 50%;
    min-width: 670px;
    width: 100%;
    float: right;
    margin-top: 10px;
    text-align: right;
}
.menus ul {
    list-style-type: none;
    margin: 0;
}
.menus > a {
    text-decoration: none;
    color: #fff;
    padding: 0.25rem 1rem;
}
.menus > a:hover,
.menus > a.active {
    color: var(--yellow);
}
</style>
