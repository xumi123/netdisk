<template>
  <div>
    <input type="text" v-model="searchKeyword" @input="searchFiles" placeholder="搜索文件或文件夹">
  </div>
</template>

<script>
export default {
  props: {
    onSearch: {
      type: Function,
      required: true
    }
  },
  data() {
    return {
      searchKeyword: ''
    };
  },
  methods: {
    searchFiles() {
      this.onSearch(this.searchKeyword);
    }
  }
};
</script>
