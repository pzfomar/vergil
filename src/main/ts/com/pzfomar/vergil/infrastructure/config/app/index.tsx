/* @refresh reload */
import { render } from "solid-js/web";

import "./index.css";
import Router from "../router/router";

const root = document.getElementById("root");
render(() => <Router />, root!);
