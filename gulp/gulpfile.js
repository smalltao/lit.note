var gulp = require('gulp');
var gulpif = require('gulp-if');//为功能添加执行条件判断 ，和程序中的if语义相同
var sprity = require('sprity'); //自动雪碧图
//var htmlIncluder = require('gulp-tag-include');//模板插件https://github.com/RodeyManager/gulp-tag-include html中引入，可带参数
var fileIncluder = require('gulp-file-include'); // https://www.npmjs.com/package/gulp-file-include 实现类似后端模板一样的代码分离，代码重用
var imagemin = require('gulp-imagemin'); //压缩图片
var pngquant = require('imagemin-pngquant'); //深度压缩png
var imageminMozjpeg = require('imagemin-mozjpeg'); //压缩jpg
var postcss = require('gulp-postcss');//用下一代的css写法兼容现在浏览器;让样式补全浏览器前缀；让样式兼容ie
var sass = require('gulp-sass');//编译sass
var autoprefixer = require('autoprefixer');// 解析样式文件并添加浏览器前缀到css规则里
//var dgbl = require("del-gulpsass-blank-lines");// https://github.com/duan602728596/del-gulpsass-blank-lines  删空格
var atImport = require('postcss-import'); //合并import进来的css文件
var uglify = require('gulp-uglify'); //压缩js
var browserSync = require('browser-sync').create(); //浏览器自动刷新
var reload = browserSync.reload;

var processors = [
  autoprefixer({
    browsers: ['iOS >= 7','Android >= 4.1']
  }),
  atImport
];

gulp.task('sass', function() {
   return gulp.src('src/css/**/*.scss')
    .pipe(sass({
        outputStyle: 'compact'// compact compressed
    }).on('error', sass.logError))
    .pipe(postcss(processors))
    //.pipe(dgbl())
    .pipe(gulp.dest('dist/css'))
    .pipe(reload({ stream: true }))
});


gulp.task('sprite', function() {
    return sprity.src({
            src: './src/asset/sprite/**/*.png',
            cssPath: '../img',
            dimension: [{
                ratio: 1,
                dpi: 72
            }, {
                ratio: 2,
                dpi: 192
            }], //不需要适配高清图请关闭此选项，否则会出错
            engine: 'gm',
            format: 'png', // because sprity name it with uppercase: https://github.com/sprity/sprity/issues/28
            processor: 'sass', // make sure you have installed sprity-sass
            style: '_sprite.scss',
            orientation: 'binary-tree',
            prefix: 'sprite',
            split: true
        })
        // .pipe(plumber())
        .pipe(gulpif('*.png', gulp.dest('./src/img/'), gulp.dest('./src/css/')))
});


gulp.task('html', function() {
    gulp.src(['src/**/*.html','src/*.html'])
        .pipe(fileIncluder())
        .pipe(gulp.dest('dist'))
        .on('end', reload)
});



gulp.task('js', function() {
    return gulp.src(['src/js/**/*.js','src/js/*.js'])
        // .pipe(plumber())
        //.pipe(uglify())
        .pipe(gulp.dest('dist/js'))
        .on('end', reload)
});

gulp.task('imagemin', function() {
    return gulp.src('src/img/**/*')
        // .pipe(plumber())
        .pipe(imagemin({
            use: [pngquant(), imageminMozjpeg({ quality: 80 })]
        }))
        .pipe(gulp.dest('dist/img'))
        .pipe(reload({ stream: true }))
});

gulp.task('default', function() {
    gulp.watch('src/css/**/*.scss', ['sass']);
    gulp.watch(['src/**/*.html','src/*.html'], ['html']);
    gulp.watch('src/asset/sprite/**/*.png', ['sprite']);
    gulp.watch('src/img/**/*', ['imagemin']);
    gulp.watch(['src/js/*.js', 'src/js/**/*.js'], ['js']);
    browserSync.init({
        server: {
            baseDir: 'dist',
            directory: true
        },
        open: "external"
    });
});
