//导入js文件
import saveAs from "file-saver"
import $ from 'jquery'
if (typeof jquery !== "undefined" && typeof saveAs !== "undefined") {
    (function() {
        $.fn.wordExport = function(fileName,rules) {
            // fileName = typeof fileName !== 'undefined' ? fileName : "jquery-Word-Export";
            // var static_ = {
            //     mhtml: {
            //         top: "Mime-Version: 1.0\nContent-Base: " + location.href + "\nContent-Type: Multipart/related; boundary=\"NEXT.ITEM-BOUNDARY\";type=\"text/html\"\n\n--NEXT.ITEM-BOUNDARY\nContent-Type: text/html; charset=\"utf-8\"\nContent-Location: " + location.href + "\n\n<!DOCTYPE html>\n<html>\n_html_</html>",
            //         head: "<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n<style>\n_styles_\n</style>\n</head>\n",
            //         body: "<body>_body_</body>"
            //     }
            // };
            // var options = {
            //     maxWidth: 624
            // };

            // var markup = $(this).clone();
            //
            //
            // markup.each(function() {
            //     var self = $(this);
            //     if (self.is(':hidden')||self.is('.nofile'))
            //         self.remove();
            // });

            // Embed all images using Data URLs
            // var images = Array();
            // var img = markup.find('img');

            // for (var i = 0; i < img.length; i++) {
            //     // Calculate dimensions of output image
            //     var w = Math.min(img[i].width, options.maxWidth);
            //     var h = img[i].height * (w / img[i].width);
            //     // Create canvas for converting image to data URL
            //     var canvas = document.createElement("CANVAS");
            //     canvas.width = w;
            //     canvas.height = h;
            //     // 设置允许跨域
            //     $(img[i]).attr('crossOrigin', 'anonymous');
            //
            //     // Draw image to canvas
            //     var context = canvas.getContext('2d');
            //     context.drawImage(img[i], 0, 0, w, h);
            //     // Get data URL encoding of image
            //     var uri = canvas.toDataURL("image/png");
            //     $(img[i]).attr("src", img[i].src);
            //     img[i].width = w;
            //     img[i].height = h;
            //     // Save encoded image to array
            //     images[i] = {
            //         type: uri.substring(uri.indexOf(":") + 1, uri.indexOf(";")),
            //         encoding: uri.substring(uri.indexOf(";") + 1, uri.indexOf(",")),
            //         location: $(img[i]).attr("src"),
            //         data: uri.substring(uri.indexOf(",") + 1)
            //     }
            // }
            // Prepare bottom of mhtml file with image data
            // var mhtmlBottom = "\n";
            // for (var i = 0; i < images.length; i++) {
            //     mhtmlBottom += "--NEXT.ITEM-BOUNDARY\n";
            //     mhtmlBottom += "Content-Location: " + images[i].location + "\n";
            //     mhtmlBottom += "Content-Type: " + images[i].type + "\n";
            //     mhtmlBottom += "Content-Transfer-Encoding: " + images[i].encoding + "\n\n";
            //     mhtmlBottom += images[i].data + "\n\n";
            // }
            // mhtmlBottom += "--NEXT.ITEM-BOUNDARY--";
            //
            // //TODO: load css from included stylesheet
            // var styles = rules;
            //
            // // Aggregate parts of the file together
            // var fileContent = static_.mhtml.top.replace("_html_", static_.mhtml.head.replace("_styles_", styles) + static_.mhtml.body.replace("_body_", window.localStorage.getItem('download'))) + mhtmlBottom;

            // Create a Blob with the file contents
            var blob = new Blob([window.localStorage.getItem('download')], {
                type: "application/msword;charset=utf-8"
            });
            saveAs.saveAs(blob, fileName + ".doc");
        };
    })(jquery);
} else {
    if (typeof jquery === "undefined") {
        console.error("jquery Word Export: missing dependency (jquery)");
    }
    if (typeof saveAs === "undefined") {
        console.error("jquery Word Export: missing dependency (FileSaver.js)");
    }
}

