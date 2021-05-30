<template>
    <div>
        <h3>欢迎来到控制台</h3>
        <img src="../../../../admin/public/static/image/girl.jpg" style="width: auto;height:600px;">
    </div>
</template>

<script>
    export default {
        name: 'welcome',
        mounted() {
            this.$parent.activeSidebar("welcome-sidebar");
        },
        methods:{
        }
    }
</script>

<style>
    h3 {
        text-align: center;
    }
</style>