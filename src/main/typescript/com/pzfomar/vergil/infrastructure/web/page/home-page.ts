import typescriptLogo from "/img/typescript.svg";
import viteLogo from "/img/vite.svg";

export class HomePage extends HTMLElement {
  connectedCallback() {
    this.innerHTML = `
    <style>
      @scope {
        h1 {
          background: hotpink;
          color: white;
        }
      }
    </style>
    <ion-header>
      <ion-toolbar>
        <ion-title>HOME</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content fullscreen="true" class="ios ion-padding">
      <a href="https://vite.dev" target="_blank">
        <img src="${viteLogo}" class="logo" alt="Vite logo" />
      </a>
      <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript" target="_blank">
        <img src="${typescriptLogo}" class="logo vanilla" alt="TypeScript logo" />
      </a>
      <h1>Hello Vite!</h1>
      <div class="card">
        <button id="counter" type="button"></button>
      </div>
      <p class="read-the-docs">
        Click on the Vite logo to learn more
      </p>
    </ion-content>
    `;
  }
}

export const HOME_PAGE = "home-page";
customElements.define(HOME_PAGE, HomePage);
