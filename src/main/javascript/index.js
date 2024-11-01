import { PAGE_EDITOR } from "./js/page-editor.js";

const routes = [
  {
    url: "/",
    component: PAGE_EDITOR,
  },
];

document.querySelector("#app").innerHTML = `
<ion-router class="ios">
  ${routes.map(
    ({ url, component }) =>
      `<ion-route url="${url}" component="${component}"></ion-route>`
  )}
</ion-router>
<ion-router-outlet></ion-router-outlet>
`;
