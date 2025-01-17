var __defProp = Object.defineProperty;
var __getOwnPropDesc = Object.getOwnPropertyDescriptor;
var __getOwnPropNames = Object.getOwnPropertyNames;
var __hasOwnProp = Object.prototype.hasOwnProperty;
var __export = (target, all) => {
  for (var name in all)
    __defProp(target, name, { get: all[name], enumerable: true });
};
var __copyProps = (to, from, except, desc) => {
  if (from && typeof from === "object" || typeof from === "function") {
    for (let key of __getOwnPropNames(from))
      if (!__hasOwnProp.call(to, key) && key !== except)
        __defProp(to, key, { get: () => from[key], enumerable: !(desc = __getOwnPropDesc(from, key)) || desc.enumerable });
  }
  return to;
};
var __toCommonJS = (mod) => __copyProps(__defProp({}, "__esModule", { value: true }), mod);

// src/main.ts
var main_exports = {};
__export(main_exports, {
  default: () => WordNetPlugin
});
module.exports = __toCommonJS(main_exports);
var import_obsidian5 = require("obsidian");

// src/EditSuggest.ts
var import_obsidian = require("obsidian");
var TheEditorSuggestor = class extends import_obsidian.EditorSuggest {
  constructor(plugin) {
    super(plugin.app);
    this.plugin = plugin;
    this.updatePattern();
  }
  updatePattern() {
    this.pattern = new RegExp(
      `.*${this.plugin.settings.slashCommandShortcut}(.*)$`
    );
  }
  onTrigger(cursor, editor, _file) {
    if (this.plugin.settings.slashCommandEnabled === false) return;
    const range = editor.getRange(
      { line: cursor.line, ch: 0 },
      { line: cursor.line, ch: cursor.ch }
    );
    const testResults = this.pattern.exec(range);
    if (!testResults) return null;
    const suggestText = testResults[1];
    this.lastEditorSuggestTriggerInfo = {
      start: {
        line: cursor.line,
        ch: cursor.ch - suggestText.length - this.plugin.settings.slashCommandShortcut.length
      },
      end: { line: cursor.line, ch: cursor.ch },
      query: testResults[1]
    };
    return this.lastEditorSuggestTriggerInfo;
  }
  getSuggestions(context) {
    return this.plugin.dictionarySuggestor.query(context.query);
  }
  renderSuggestion(item, el) {
    el.createEl("b", { text: item.Term });
    el.createEl("br");
    el.appendText(item.Definition);
  }
  selectSuggestion(item, evt) {
    const currentView = this.plugin.app.workspace.getActiveViewOfType(import_obsidian.MarkdownView);
    this.close();
    if (evt.ctrlKey) {
      new import_obsidian.Notice(`${item.Term} 
${item.Definition}`, 6e4);
      currentView.editor.replaceRange(
        "",
        this.lastEditorSuggestTriggerInfo.start,
        this.lastEditorSuggestTriggerInfo.end
      );
    } else
      currentView.editor.replaceRange(
        this.plugin.renderDefinitionFromTemplate(item.Term, item.Definition),
        this.lastEditorSuggestTriggerInfo.start,
        this.lastEditorSuggestTriggerInfo.end
      );
  }
};

// src/settings.ts
var import_obsidian2 = require("obsidian");
var import_obsidian3 = require("obsidian");
var DEFAULT_SETTINGS = {
  slashCommandEnabled: true,
  slashCommandShortcut: ";;",
  insertTemplate: "**{term}**\n{definition}\n"
};
var WordNetSettingTab = class extends import_obsidian3.PluginSettingTab {
  constructor(app, plugin) {
    super(app, plugin);
    this.plugin = plugin;
  }
  display() {
    const { containerEl } = this;
    containerEl.empty();
    new import_obsidian2.Setting(containerEl).setName("Slash command").setDesc("Enable the slash command for WordNet.").addToggle((cb) => {
      cb.setValue(this.plugin.settings.slashCommandEnabled);
      cb.onChange(async (value) => {
        this.plugin.settings.slashCommandEnabled = value;
        await this.plugin.saveSettings();
      });
    });
    let cbShortcut;
    new import_obsidian2.Setting(containerEl).setName("Slash command characters").setDesc(
      "The characters that will invoke the slash command. The command character cannot be a space."
    ).addExtraButton((b) => {
      b.setIcon("reset").setTooltip("Reset to default").onClick(async () => {
        this.plugin.settings.slashCommandShortcut = DEFAULT_SETTINGS.slashCommandShortcut;
        await this.plugin.saveSettings();
        this.plugin.editSuggester.updatePattern();
        cbShortcut.setValue(this.plugin.settings.slashCommandShortcut);
      });
    }).addText((cb) => {
      cbShortcut = cb;
      cb.setValue(this.plugin.settings.slashCommandShortcut);
      cb.onChange(async (value) => {
        const newValue = value.trim().length === 0 ? DEFAULT_SETTINGS.slashCommandShortcut : value;
        this.plugin.settings.slashCommandShortcut = newValue;
        await this.plugin.saveSettings();
        this.plugin.editSuggester.updatePattern();
      });
    });
    let cbTemplate;
    new import_obsidian2.Setting(containerEl).setName("Template for inserting a definition").setDesc(
      "The template used for inserting a WordNet definition. Use {term} for the term looked up and {definition} for the defintion of that term."
    ).addExtraButton((b) => {
      b.setIcon("reset").setTooltip("Reset to default").onClick(async () => {
        this.plugin.settings.insertTemplate = DEFAULT_SETTINGS.insertTemplate;
        await this.plugin.saveSettings();
        cbTemplate.setValue(this.plugin.settings.insertTemplate);
      });
    }).addTextArea((cb) => {
      cbTemplate = cb;
      cb.setValue(this.plugin.settings.insertTemplate);
      cb.onChange(async (value) => {
        const newValue = value.trim().length === 0 ? DEFAULT_SETTINGS.insertTemplate : value;
        this.plugin.settings.insertTemplate = newValue;
        await this.plugin.saveSettings();
      });
      cb.inputEl.rows = 2;
      cb.inputEl.cols = 40;
    });
  }
};

// src/suggester.ts
var import_obsidian4 = require("obsidian");
var DictionarySuggester = class extends import_obsidian4.FuzzySuggestModal {
  constructor(plugin) {
    super(plugin.app);
    this.plugin = plugin;
    this.setPlaceholder("type word to lookup in WordNet");
    setTimeout(async () => {
      const pathWordNetJson = `${this.plugin.manifest.dir}/dict-WordNet.json`;
      const adapter = this.app.vault.adapter;
      if (await adapter.exists(pathWordNetJson)) {
        const fileWordNet = await adapter.read(pathWordNetJson);
        this.wordNet = await JSON.parse(fileWordNet);
      } else {
        if (navigator.onLine === false) {
          new import_obsidian4.Notice(
            "You do not have an internet connection, and the WordNet dictionary cannot be downloaded. Please restore your interent connection and resteart Obsidian",
            3e4
          );
          this.plugin.unload();
        } else {
          const downloadMessage = new import_obsidian4.Notice(
            "WordNet dictionary is being downloaded, this may take a few minutes. This message will disappear when the process is complete.",
            0
          );
          try {
            const response = await (0, import_obsidian4.request)({
              url: "https://github.com/TfTHacker/Obsidian-WordNet/releases/download/WordNetJson/dict-WordNet.json"
            });
            downloadMessage.hide();
            if (response === "Not Found" || response === `{"error":"Not Found"}`) {
              new import_obsidian4.Notice(
                "The WordNet dictionary file is not currently available for download. Please try again later or contact the developer on Twitter: @TfThacker for support.",
                3e4
              );
              this.plugin.unload();
            } else {
              this.wordNet = await JSON.parse(response);
              await adapter.write(
                pathWordNetJson,
                JSON.stringify(this.wordNet)
              );
            }
          } catch (e) {
            new import_obsidian4.Notice(
              `An error has occured with the download, please try again later: ${e}`
            );
            this.plugin.unload();
          }
        }
      }
      if (await adapter.exists(`${this.plugin.manifest.dir}/dict-MyDict.json`)) {
        const fileCustomDict = await adapter.read(
          `${this.plugin.manifest.dir}/dict-MyDict.json`
        );
        this.customDict = await JSON.parse(fileCustomDict);
      } else this.customDict = null;
    }, 10);
  }
  query(term) {
    const results = [];
    const searchTerm = term.toLocaleLowerCase();
    let countOfFoundMatches = 0;
    if (this.customDict != null) {
      for (let i = 0; i < this.customDict.length && countOfFoundMatches < 30; i++) {
        const item = this.customDict[i];
        if (item.SearchTerm.startsWith(searchTerm)) {
          results.push(this.customDict[i]);
          countOfFoundMatches++;
        }
      }
    }
    countOfFoundMatches = 0;
    for (let i = 0; i < this.wordNet.length && countOfFoundMatches < 20; i++) {
      const item = this.wordNet[i];
      if (item.SearchTerm.startsWith(searchTerm)) {
        results.push(this.wordNet[i]);
        countOfFoundMatches++;
      }
    }
    return results;
  }
  getItems() {
    let searchTerm = "";
    if (this.inputEl.value.trim().length === 0) {
      const currentView = this.app.workspace.getActiveViewOfType(import_obsidian4.MarkdownView);
      if (currentView != null && currentView.getMode() !== void 0 && currentView.editor.somethingSelected()) {
        searchTerm = currentView.editor.getSelection();
        this.inputEl.value = searchTerm;
        this.inputEl.setSelectionRange(0, searchTerm.length);
      }
    } else searchTerm = this.inputEl.value.trim();
    return searchTerm === "" ? [] : this.query(searchTerm);
  }
  getItemText(item) {
    return item.SearchTerm;
  }
  // @ts-ignore
  onChooseItem(item, evt) {
  }
  renderSuggestion(item, el) {
    el.createEl("b", { text: item.item.Term });
    el.createEl("br");
    el.appendText(item.item.Definition);
  }
  onChooseSuggestion(item, evt) {
    const currentView = this.plugin.app.workspace.getActiveViewOfType(import_obsidian4.MarkdownView);
    if (currentView != null && currentView.getMode() === "source")
      currentView.editor.replaceSelection(
        this.plugin.renderDefinitionFromTemplate(
          item.item.Term,
          item.item.Definition
        )
      );
    else new import_obsidian4.Notice(`${item.item.Term} 
${item.item.Definition}`, 1e4);
  }
};

// src/main.ts
var WordNetPlugin = class extends import_obsidian5.Plugin {
  configureRibbonCommand() {
    this.ribbonIcon = this.addRibbonIcon(
      "book-open-check",
      "WordNet Dictionary",
      async () => {
        this.dictionarySuggestor.open();
      }
    );
  }
  async onload() {
    console.log("loading WordNet plugin");
    await this.loadSettings();
    this.addSettingTab(new WordNetSettingTab(this.app, this));
    this.dictionarySuggestor = new DictionarySuggester(this);
    if (this.settings.enableRibbon) this.configureRibbonCommand();
    this.addCommand({
      id: "open-wordnet-suggestor",
      name: "Look up a word",
      callback: () => {
        this.dictionarySuggestor.open();
      }
    });
    this.editSuggester = new TheEditorSuggestor(this);
    this.registerEditorSuggest(this.editSuggester);
  }
  onunload() {
    console.log("unloading WordNet plugin");
  }
  renderDefinitionFromTemplate(term, definition) {
    return this.settings.insertTemplate.replace("{term}", term).replace("{definition}", definition);
  }
  async loadSettings() {
    this.settings = Object.assign({}, DEFAULT_SETTINGS, await this.loadData());
  }
  async saveSettings() {
    await this.saveData(this.settings);
  }
};

/* nosourcemap */