<template>
  <div v-if="useSystemStore().isLoading" class="absolute top-0 start-0 end-0 bottom-0 z-[9999] flex justify-center items-center"
       style="background-color: rgba(0, 0, 0, .5)">
    <div class="lds-ring">
      <div></div>
      <div></div>
      <div></div>
      <div></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {useSystemStore} from "~/stores/system";
</script>

<style type="text/css">
.lds-ring {
  display: inline-block;
  position: relative;
  width: 45px;
  height: 45px;
}

.lds-ring div {
  box-sizing: border-box;
  display: block;
  position: absolute;
  width: 45px;
  height: 45px;
  margin: 1px;
  border: 6px solid #fff;
  border-radius: 50%;
  animation: lds-ring 1.2s cubic-bezier(0.5, 0, 0.5, 1) infinite;
  border-color: #fff transparent transparent transparent;
}

.lds-ring div:nth-child(1) {
  animation-delay: -0.45s;
}

.lds-ring div:nth-child(2) {
  animation-delay: -0.3s;
}

.lds-ring div:nth-child(3) {
  animation-delay: -0.15s;
}

@keyframes lds-ring {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>