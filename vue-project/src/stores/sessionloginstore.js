import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const usesessionStore = defineStore("session", () => {
  let lgin = ref(false);

  function chageSession() {
    if(lgin.value) {
        lgin.value = false
    } else {
        lgin.value = true
    }
  }
  const getlgin = computed(() => lgin.value);
  return { lgin, chageSession, getlgin };
});
