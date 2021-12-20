<template>
  <ion-header>
    <ion-toolbar>
      <ion-title>{{ modalTitle }}</ion-title>
      <ion-buttons @click="closeModal" slot="end">
        <ion-icon :icon="closeOutline" slot="start"></ion-icon>
      </ion-buttons>
    </ion-toolbar>
  </ion-header>
  <ion-content class="ion-padding">
    <slot ></slot>
  </ion-content>
</template>

<script>
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar,
  IonIcon,
  IonButtons,
  modalController,
} from "@ionic/vue";
import { closeOutline } from "ionicons/icons";

export default {
  name: "Modal",
  props: ["modalTitle"],
  components: { IonContent, IonHeader, IonTitle, IonToolbar, IonIcon, IonButtons },
  data() {
    return {
      closeOutline,
    };
  },
  methods: {
    async closeModal() {
      await modalController.dismiss();
    }
  }
};
</script>