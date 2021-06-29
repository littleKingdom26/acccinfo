<template>
    <div class="text-center mb-5 Staatliches goBack">
        <div @click="$router.go(-1)" style="display: inline-block">
            <img src="/vue_assets/img/LEAGUE INFORMATION TAB_ARROW@3x.png" />
            <div>BACK</div>
        </div>
    </div>
</template>

<script>
export default {};
</script>

<style scoped>
.goBack > div > * {
    cursor: pointer;
}
.goBack img {
    max-width: 60px;
    margin-bottom: 1em;
}
</style>
