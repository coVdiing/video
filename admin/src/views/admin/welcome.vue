<template>
    <div>
        <h3>欢迎来到控制台</h3>
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