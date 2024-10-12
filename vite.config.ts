import { defineConfig } from 'vite'
import solid from 'vite-plugin-solid'
import obfuscatorPlugin from "vite-plugin-javascript-obfuscator";

export default defineConfig({
  plugins: [
    solid(),
    obfuscatorPlugin({
      include: ["src/main/ts/**"],
      exclude: [/node_modules/],
      apply: "build",
      debugger: true,
      options: {
        debugProtection: true,
      },
    }),
  ],
  server: {
    //open: 'templates/index.html',
  },
  build: {
    outDir: 'src/main/resources/static/',
    emptyOutDir: true,
    rollupOptions: {
      //input: 'templates/index.html',
      output: {
        //dir: "src/main/resources/",
        //file: "c/index.html",
        assetFileNames: (assetInfo) => {
          let other = 'other';
          let extType = (assetInfo.name || `${other}.${other}`).split('.').at(1) || other;
          if (/png|jpe?g|svg|gif|tiff|bmp|ico/i.test(extType)) {
            extType = 'img';
          } else if (/css|sass|scss/i.test(extType)) {
            extType = 'css';
          } else if (/woff|woff2|eot|ttf|otf/i.test(extType)) {
            extType = 'fonts';
          } else if (/json/.test(extType)) {
            extType = 'anim';
          }
          return `${extType}/[name][extname]`;
        },
        chunkFileNames: "js/[name].js",
        entryFileNames: "js/[name].js",
        sourcemap: true,
      },
    },
  },
});
