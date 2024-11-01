import "../css/page-editor.css";

export class PageEditor extends HTMLElement {
  static ioSegmentValue = "html";
  static editorJs;

  tools() {
    return {
      header: {
        class: Header,
        inlineToolbar: ["marker", "link"],
        config: {
          placeholder: "Header",
        },
        shortcut: "CMD+SHIFT+H",
      },
      image: {
        class: InlineImage,
        inlineToolbar: true,
        config: {
          embed: {
            display: true,
          },
          unsplash: {
            appName: "your_app_name",
            apiUrl: "https://your-proxy-api-url.com",
            maxResults: 30,
          },
        },
      },
      list: {
        class: List,
        inlineToolbar: true,
        shortcut: "CMD+SHIFT+L",
      },
      marker: {
        class: Marker,
        shortcut: "CMD+SHIFT+M",
      },
      code: {
        class: CodeTool,
        shortcut: "CMD+SHIFT+C",
      },
      delimiter: Delimiter,
      inlineCode: {
        class: InlineCode,
        shortcut: "CMD+SHIFT+C",
      },
      linkTool: {
        class: LinkTool,
        config: {
          endpoint: "",
        },
      },
      embed: Embed,
      table: {
        class: Table,
        inlineToolbar: true,
        shortcut: "CMD+ALT+T",
      },
    };
  }

  data() {
    return {
      blocks: [
        {
          type: "header",
          data: {
            text: "Editor.js",
            level: 2,
          },
        },
        {
          type: "paragraph",
          data: {
            text: "Hey. Meet the new Editor. On this page you can see it in action — try to edit this text. Source code of the page contains the example of connection and configuration.",
          },
        },
        {
          type: "list",
          data: {
            items: [
              "It is a block-styled editor",
              "It returns clean data output in JSON",
              "Designed to be extendable and pluggable with a simple API",
            ],
            style: "unordered",
          },
        },
        {
          type: "delimiter",
          data: {},
        },
        {
          type: "image",
          data: {
            url: "https://www.imcrc.org/wp-content/uploads/2022/03/partner-logos@2x-codex.png",
            caption: "",
            stretched: false,
            withBorder: true,
            withBackground: false,
          },
        },
      ],
    };
  }

  static htmlMd(blocks) {
    return blocks.map((block) => {
      let htmlCode = null;
      let mdCode = null;
      let pdfCode = null;
      switch (block.type) {
        case "header":
          htmlCode = `<h${block.data.level}>${block.data.text}</h${block.data.level}>`;
          mdCode = `${Array.from({ length: block.data.level }, () => "#").join(
            ""
          )} ${block.data.text}`;
          pdfCode = htmlCode;
          return { html: htmlCode, md: mdCode, pdf: pdfCode };
        case "paragraph":
          htmlCode = `<p>${block.data.text}</p>`;
          mdCode = `${block.data.text}`;
          pdfCode = htmlCode;
          return { html: htmlCode, md: mdCode, pdf: pdfCode };
        case "list":
          htmlCode = `<ul>\n${block.data.items
            .map((item) => `<li>${item}</li>`)
            .join("\n")}\n</ul>`;
          mdCode = block.data.items.map((item) => `+ ${item}`).join("\n");
          pdfCode = htmlCode;
          return { html: htmlCode, md: mdCode, pdf: pdfCode };
        case "delimiter":
          htmlCode = `<hr style="background-color: black;">`;
          mdCode = `-----`;
          pdfCode = htmlCode;
          return { html: htmlCode, md: mdCode, pdf: pdfCode };
        case "image":
          htmlCode = `<img src="${block.data.url}" alt="${block.data.caption}" />`;
          mdCode = `![${block.data.caption}](${block.data.url})`;
          pdfCode = htmlCode;
          return { html: htmlCode, md: mdCode, pdf: pdfCode };
        case "linkTool":
          htmlCode = `<a href="${block.data.link}">${block.data.link}</a>`;
          mdCode = `[${block.data.link}](${block.data.link})`;
          pdfCode = htmlCode;
          return { html: htmlCode, md: mdCode, pdf: pdfCode };
        case "code":
          htmlCode = `<code>\n${block.data.code}\n</code>`;
          mdCode = `\`\`\`\n${block.data.code}\n\`\`\``;
          pdfCode = htmlCode;
          return { html: htmlCode, md: mdCode, pdf: pdfCode };
        case "table":
          htmlCode = `<table style="width:100%;border-collapse:collapse;">\n${block.data.content
            .slice(0, 1)
            .map(
              (tr) =>
                `<tr>\n${tr
                  .map((td) => `<th style="border:1px solid black;">${td}</th>`)
                  .join("\n")}\n</tr>`
            )
            .join("\n")}\n${block.data.content
            .slice(1)
            .map(
              (tr) =>
                `<tr>\n${tr
                  .map((td) => `<td style="border:1px solid black;">${td}</td>`)
                  .join("\n")}\n</tr>`
            )
            .join("\n")}\n</table>`;
          mdCode = `${block.data.content
            .slice(0, 1)
            .map((tr) => `| ${tr.map((td) => td).join(" | ")} |`)
            .join("\n")}\n${block.data.content
            .slice(0, 1)
            .map((tr) => `| ${tr.map(() => "---").join(" | ")} |`)
            .join("\n")}\n${block.data.content
            .slice(1)
            .map((tr) => `| ${tr.map((td) => td).join(" | ")} |`)
            .join("\n")}`;
          pdfCode = htmlCode;
          return { html: htmlCode, md: mdCode, pdf: pdfCode };
        default:
          break;
      }
    });
  }

  static codeHtmlMd(blocks) {
    let htmlMd = PageEditor.htmlMd(blocks);

    switch (PageEditor.ioSegmentValue) {
      case "html":
        document.querySelector("#code-text").innerText = htmlMd
          .map((hm) => hm.html)
          .join("\n");
        break;
      case "md":
        document.querySelector("#code-text").innerText = htmlMd
          .map((hm) => hm.md)
          .join("\n");
        break;
      case "pdf":
        document.querySelector("#code-text").innerHTML = htmlMd
          .map((hm) => hm.html)
          .join("\n");
        break;
      default:
        break;
    }
  }

  ioSegmentChanged(event) {
    PageEditor.ioSegmentValue = event.detail.value;
    PageEditor.editorJs
      .save()
      .then(({ blocks }) => PageEditor.codeHtmlMd(blocks))
      .catch((error) => console.error("Saving error", error));
  }

  static downloadFile(type, content) {
    let download = null;
    switch (type) {
      case "html":
        download = document.createElement("a");
        download.href = `data:attachment/${type}, ${encodeURI(
          `<!DOCTYPE html>\n<html>\n<body>\n${content}\n</body>\n</html>\n`
        )}`;
        download.target = "_blank";
        download.download = `README.${type}`;
        download.click();
        break;
      case "md":
        download = document.createElement("a");
        download.href = `data:attachment/${type}, ${encodeURI(content)}`;
        download.target = "_blank";
        download.download = `README.${type}`;
        download.click();
        break;
      case "pdf":
        download = document.createElement("div");
        download.innerHTML = `<!DOCTYPE html>\n<html>\n<body>\n${content}\n</body>\n</html>\n`;
        html2pdf()
          .set({
            margin: 0.5,
            filename: "README.pdf",
            image: { type: "jpeg", quality: 0.1 },
            html2canvas: { scale: 2, useCORS: true },
            jsPDF: { unit: "in", format: "a4", orientation: "p" },
          })
          .from(download)
          .save();
        break;
      default:
        break;
    }
  }

  connectedCallback() {
    PageEditor.editorJs = new EditorJS({
      readOnly: false,
      holder: "editor-js",
      tools: this.tools(),
      data: this.data(),
      onReady: function () {
        PageEditor.editorJs
          .save()
          .then(({ blocks }) => PageEditor.codeHtmlMd(blocks))
          .catch((error) => console.error("Saving error", error));
      },
      onChange: async function (api, event) {
        api.saver
          .save()
          .then(({ blocks }) => PageEditor.codeHtmlMd(blocks))
          .catch((error) => console.error("Saving error", error));
      },
    });

    this.innerHTML = `
    <ion-header>
      <ion-toolbar>
        <ion-title>HTML - MD</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content class="ios ion-padding">
      <ion-alert id="ion-download-file-alert" trigger="download-file-alert" header="ARCHIVO"></ion-alert>
      <ion-grid>
        <ion-row>
          <ion-col size="12" size-sm="6">
            <div id="editor-js"></div>
          </ion-col>
          <ion-col size="12" size-sm="6">
            <div id="view-code">
              <ion-button id="download-file-alert" size="small" expand="block" color="secondary" class="ios">ARCHIVO</ion-button>
              <ion-segment value="html" class="ios" id="ioSegment">
                <ion-segment-button value="html" class="ios">
                  <ion-label>HTML</ion-label>
                </ion-segment-button>
                <ion-segment-button value="md" class="ios">
                  <ion-label>MD</ion-label>
                </ion-segment-button>
                <ion-segment-button value="pdf" class="ios">
                  <ion-label>PDF</ion-label>
                </ion-segment-button>
              </ion-segment>
              <br />
              <code id="code-text"></code>
            </div>
          </ion-col>
        </ion-row>
      </ion-grid>
    </ion-content>
    `;

    this.querySelector("#ioSegment").addEventListener(
      "ionChange",
      this.ioSegmentChanged
    );

    const alert = document.querySelector("#ion-download-file-alert");
    alert.buttons = [
      {
        text: "Descargar html",
        role: "html",
      },
      {
        text: "Descargar md",
        role: "md",
      },
      {
        text: "Descargar pdf",
        role: "pdf",
      },
      {
        text: "Cancelar",
        role: "cancel",
        handler: () => {},
      },
    ];

    alert.addEventListener("ionAlertDidDismiss", (ev) =>
      PageEditor.editorJs
        .save()
        .then(({ blocks }) =>
          PageEditor.downloadFile(
            ev.detail.role,
            `${PageEditor.htmlMd(blocks)
              .map((hm) => hm[ev.detail.role])
              .join("\n")}\n`
          )
        )
        .catch((error) => console.error("Saving error", error))
    );
  }
}

export const PAGE_EDITOR = "page-editor";
customElements.define(PAGE_EDITOR, PageEditor);
