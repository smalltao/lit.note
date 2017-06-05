# 安装atom遇到的问题
1. 安装package时提示 `unable to verify the first certificate` 不能验证第一证书
> 解决办法， 查看官方：https://github.com/atom/apm#behind-a-firewall

```
apm config set strict-ssl false
```
2. 增强预览(markdown-preview-plus)

> Atom自带的Markdown预览插件markdown-preview功能比较简，markdown-preview-plus对其做了功能扩展和增强。

```
1. 支持预览实时渲染。(Ctrl + Shift + M)
2. 支持Latex公式。(Ctrl + Shift + X)
3. 使用该插件前，需要先禁用markdown-preview。
```
3. 同步滚动(markdown-scroll-sync)

> 同步滚动是Markdown编辑器的必备功能，方便翻阅文档修改时能快速定位到预览的位置。
> markdown-scroll-sync不仅支持同步滚动，在光标位置发生变更时也会同步滚动，这个功能在很多Markdown编辑器中不具备。

4. 代码增强(language-markdown)

> 一般的Markdown编辑器提供了代码着色等基本功能，language-markdown除了能给代码着色，还提供了快捷的代码片段生成等功能。

5. 图片粘贴(markdown-image-paste)

>图片功能支持的好坏直接决定了我是否选择使用一个Markdown编辑器。也有不少编辑器和在线的图床绑定，但是这种方式受限于网络。虽然Markdown支持插入本地图片，但是每次插入新图片都是一堆重复操作：截图－命名－保存－插入。markdown-image-paste将这些操作一步完成：

```
使用截图工具将图片复制到系统剪切板。
在Markdown新起一行输入文件名。
Ctrl + V 会自动把图片保存到Markdown文件相同目录下(因此要求Markdown文件应该先保存)，并命名为刚输入的文件名，同时在刚才输入文件名行处生成img标签。
```

6. 表格编辑(markdown-table-editor)
> 一直对Markdown的table语法很无爱，直到遇到了markdown-table-editor，这操作效率简直炸了！文字已经不能表达我的激动之情了，直接看图吧。

7. pdf导出(markdown-themeable-pdf、pdf-view)

> 不少Markdown编辑器都会提供pdf导出功能，甚至将其作为收费功能。而Atom的markdown-themeable-pdf、pdf-view插件可以轻松实现pdf导出和预览功能。

8. git-plus https://atom.io/packages/git-plus712

> 可以直接在 Atom 的命令面板中运行 git commit, git push 等常用命令，可以使用 Atom 来编辑 Commit Message, 查看 Diff, 查看文件历史等。有了这个插件就不需要离开 Atom 去 Shell 或者 GUI 来操作 Git 了。

9. git-projects https://atom.io/packages/git-projects107

> 可以用 ctrl-alt-o 这个快捷键搜索磁盘（默认是 ~/repos）上的 Git 仓库，形成一个列表供你快速打开一个 Atom 窗口来编辑这个项目。有了这个插件可以非常快速地打开某个项目，项目比较多也不必找来找去（可以设置按照上次修改时间排序）。

10. merge-conflicts https://atom.io/packages/merge-conflicts107

> 在使用 Git 进行合并和 rebase 的时候可以用 alt-m d 来激活这个插件，它会列出所有冲突的文件，将每一处冲突高亮，同时有按钮和快捷键供你快速选用某个版本，在你解决所有冲突后会提示你进行 Commit. 有了这个插件再也不同担心出冲突的时候看瞎眼了。
