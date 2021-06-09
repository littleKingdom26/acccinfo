<template>
    <b-container>
        <b-row align-v="center" class='notFound'>
            <b-col class="text-center">
                <h1>Page Not Found</h1>
                <p>
                    잘못된 접근입니다. 주소를 확인해주세요.
                </p>
                <b-button variant="link" @click="$router.go(-1)">뒤로가기</b-button>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
export default {};
</script>

<style scoped>
.notFound {
    height: 100vh;
}
</style>
