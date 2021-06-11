<template>
    <b-pagination
        v-model="lCurrentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls=""
        first-text="<<"
        prev-text="<"
        next-text=">"
        last-text=">>"
        @change="onClickPage"
    ></b-pagination>
</template>

<script>
export default {
    props: {
        currentPage: {
            type: Number,
            default: 1,
        },
        rows: {
            type: Number,
            default: 0,
        },
        perPage: {
            type: Number,
            default: 20,
        },
        onClickPage: {
            type: Function,
        },
    },
    mounted() {
        this.lCurrentPage = this.currentPage;
    },
    data() {
        return {
            lCurrentPage: 1,
        };
    },
};
</script>

<style scoped>
* >>> .page-item > .page-link {
    background-color: transparent;
    color: #fff;
    border: 0;
}
* >>> .page-item.disabled > .page-link {
    color: #666;
}
* >>> .page-item.active .page-link {
    background-color: transparent;
    color: var(--yellow);
    border: 0;
}
</style>
