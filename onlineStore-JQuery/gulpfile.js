var gulp = require('gulp');
var sass = require('gulp-sass');
var gutil = require('gulp-util');

gulp.task('copy', function(){
    gulp.src('app.js').pipe(gulp.dest('assets'));
});

gulp.task('sass', function(){
    gulp.src('stylemain.scss').pipe(sass({style: 'expanded'})).on('error', gutil.log).pipe(gulp.dest('assets'));
});