import { HashRouter } from "@solidjs/router";

import Home from "../../page/home/Home";

const routes = [
  {
    path: "/",
    component: Home,
  },
  {
    path: "/hello-world",
    component: () => <h1>Hello, World!</h1>,
  },
];

function Router() {
  return <HashRouter>{routes}</HashRouter>;
}

export default Router;
