<template>
    <div id="app">
        <router-view />
    </div>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Inter:wght@100;400;700;900&family=Staatliches&display=swap");
@import url("https://fonts.googleapis.com/earlyaccess/jejugothic.css");

* {
    --yellow: #e8af05;
}
html,
body {
    background-color: #000;
    margin: 0;
    padding: 0;
    min-width: 1320px;
}
#app {
    font-family: "Jeoju Gthic", Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #fff;
}

.Staatliches,
.Staatliches * {
    font-family: Staatliches, "Jeoju Gthic", Arial, sans-serif;
}
.Inter,
.Inter * {
    font-family: "Inter", sans-serif, Helvetica, Arial, sans-serif;
}
.JeojuGthic,
.JeojuGthic * {
    font-family: "Jeoju Gthic", Avenir, Helvetica, Arial, sans-serif;
}
.text-left {
    text-align: left !important;
}
.text-right {
    text-align: right !important;
}

#nav {
    padding: 30px;
}

#nav a {
    font-weight: bold;
    color: #2c3e50;
}

#nav a.router-link-exact-active {
    color: #42b983;
}
</style>
