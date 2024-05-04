<template>
  <div>
    <Calendar></Calendar>
    
    <WorkSpace></WorkSpace>
  </div>
</template>

<script>

  import Calendar from './calendar.vue'
  import WorkSpace from './workspace/index.vue'
  export default{
    
    name: 'index',

    components: {
      Calendar,
      WorkSpace
    },
  }

</script>