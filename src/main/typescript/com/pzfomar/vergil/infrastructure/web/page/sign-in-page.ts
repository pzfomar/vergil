import SignInService from "../../../application/service/sign-in-service";
import { alertError } from "../config/alert-config";
import typescriptLogo from "/img/typescript.svg";

export const SIGN_IN_PAGE = "sign-in-page";
window.customElements.define(
  SIGN_IN_PAGE,
  class extends HTMLElement {
    connectedCallback(): void {
      this.innerHTML = `
      <style>
        @scope {
          #a-avatar {
            display: flex;
            justify-content: center;
            margin: 5% 0% 5% 0%;
          }
          #img-avatar {
            width: 20%;
            height: 20%;
          }
        }
      </style>
      <ion-header>
      </ion-header>
      <ion-content fullscreen="true" class="ios ion-padding">
        <form id="signInForm" name="signInForm" action="javascript:;">
          <a id="a-avatar" href="https://developer.mozilla.org/en-US/docs/Web/JavaScript" target="_blank">
            <ion-img id="img-avatar" src="${typescriptLogo}" alt="typescript Logo"></ion-img>
          </a>

          <ion-item>
            <ion-input name="emailForm" label-placement="stacked" type="email" placeholder="email@domain.com" required="true">
              <div slot="label">Email <ion-text color="danger">(Required)</ion-text></div>
            </ion-input>
          </ion-item>

          <ion-item>
            <ion-input name="passwordForm" label-placement="stacked" type="password" placeholder="**********" required="true">
              <div slot="label">Password <ion-text color="danger">(Required)</ion-text></div>
              <ion-input-password-toggle slot="end" color="medium"></ion-input-password-toggle>
            </ion-input>
          </ion-item>

          <ion-grid>
            <ion-row>
              <ion-col size="12" size-sm="4">
                <ion-button expand="block" size="small" color="medium" type="submit" value="Submit">sign in</ion-button>
              </ion-col>

              <ion-col size="12" size-sm="4">
                <ion-button expand="block" size="small" color="medium">sign up</ion-button>
              </ion-col>

              <ion-col size="12" size-sm="4">
                <ion-button expand="block" size="small" color="secondary">
                  <ion-icon name="logo-facebook"></ion-icon>
                </ion-button>
              </ion-col>
            </ion-row>
          </ion-grid>
        </form>
      </ion-content>
    `;
      document.forms
        .namedItem("signInForm")!
        .addEventListener("submit", () =>
          SignInService.signIn(
            document.forms.namedItem("signInForm")!["emailForm"].value,
            document.forms.namedItem("signInForm")!["passwordForm"].value,
            () => (window.location.href = "/#/home"),
            alertError
          )
        );
    }
  }
);
