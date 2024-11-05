import { HOME_PAGE } from "../page/home-page.ts";
import { SIGN_IN_PAGE } from "../page/sign-in-page.ts";

export const RoutesConfig = [
  {
    url: "/home",
    component: HOME_PAGE,
  },
  {
    url: "/sign-in",
    component: SIGN_IN_PAGE,
  },
];
