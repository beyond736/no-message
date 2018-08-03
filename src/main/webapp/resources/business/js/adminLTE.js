/*! AdminLTE app.js
 * ================
 * Main JS application file for AdminLTE v2. This file
 * should be included in all pages. It controls some layout
 * options and implements exclusive AdminLTE plugins.
 *
 * @Author  Almsaeed Studio
 * @Support <http://www.almsaeedstudio.com>
 * @Email   <abdullah@almsaeedstudio.com>
 * @version 2.3.8
 * @license MIT <http://opensource.org/licenses/MIT>
 */
function _init() {
    "use strict";
    $.AdminLTE.layout = {
        activate: function () {
            var a = this;
            a.fix(), a.fixSidebar(), $("body, html, .wrapper").css("height", "auto"), $(window, ".wrapper").resize(function () {
                a.fix(), a.fixSidebar()
            })
        }, fix: function () {
            $(".layout-boxed > .wrapper").css("overflow", "hidden");
            var a = $(".main-footer").outerHeight() || 0, b = $(".main-header").outerHeight() + a,
                c = $(window).height(), d = $(".sidebar").height() || 0;
            if ($("body").hasClass("fixed")) $(".content-wrapper, .right-side").css("min-height", c - a); else {
                var e;
                c >= d ? ($(".content-wrapper, .right-side").css("min-height", c - b), e = c - b) : ($(".content-wrapper, .right-side").css("min-height", d), e = d);
                var f = $($.AdminLTE.options.controlSidebarOptions.selector);
                "undefined" != typeof f && f.height() > e && $(".content-wrapper, .right-side").css("min-height", f.height())
            }
        }, fixSidebar: function () {
            return $("body").hasClass("fixed") ? ("undefined" == typeof $.fn.slimScroll && window.console && window.console.error("Error: the fixed layout requires the slimscroll plugin!"), void($.AdminLTE.options.sidebarSlimScroll && "undefined" != typeof $.fn.slimScroll && ($(".sidebar").slimScroll({destroy: !0}).height("auto"), $(".sidebar").slimScroll({
                height: $(window).height() - $(".main-header").height() + "px",
                color: "rgba(0,0,0,0.2)",
                size: "3px"
            })))) : void("undefined" != typeof $.fn.slimScroll && $(".sidebar").slimScroll({destroy: !0}).height("auto"))
        }
    }, $.AdminLTE.pushMenu = {
        activate: function (a) {
            var b = $.AdminLTE.options.screenSizes;
            $(document).on("click", a, function (a) {
                a.preventDefault(), $(window).width() > b.sm - 1 ? $("body").hasClass("sidebar-collapse") ? $("body").removeClass("sidebar-collapse").trigger("expanded.pushMenu") : $("body").addClass("sidebar-collapse").trigger("collapsed.pushMenu") : $("body").hasClass("sidebar-open") ? $("body").removeClass("sidebar-open").removeClass("sidebar-collapse").trigger("collapsed.pushMenu") : $("body").addClass("sidebar-open").trigger("expanded.pushMenu")
            }), $(".content-wrapper").click(function () {
                $(window).width() <= b.sm - 1 && $("body").hasClass("sidebar-open") && $("body").removeClass("sidebar-open")
            }), ($.AdminLTE.options.sidebarExpandOnHover || $("body").hasClass("fixed") && $("body").hasClass("sidebar-mini")) && this.expandOnHover()
        }, expandOnHover: function () {
            var a = this, b = $.AdminLTE.options.screenSizes.sm - 1;
            $(".main-sidebar").hover(function () {
                $("body").hasClass("sidebar-mini") && $("body").hasClass("sidebar-collapse") && $(window).width() > b && a.expand()
            }, function () {
                $("body").hasClass("sidebar-mini") && $("body").hasClass("sidebar-expanded-on-hover") && $(window).width() > b && a.collapse()
            })
        }, expand: function () {
            $("body").removeClass("sidebar-collapse").addClass("sidebar-expanded-on-hover")
        }, collapse: function () {
            $("body").hasClass("sidebar-expanded-on-hover") && $("body").removeClass("sidebar-expanded-on-hover").addClass("sidebar-collapse")
        }
    }, $.AdminLTE.tree = function (a) {
        var b = this, c = $.AdminLTE.options.animationSpeed;
        $(document).off("click", a + " li a").on("click", a + " li a", function (a) {
            var d = $(this), e = d.next();
            if (e.is(".treeview-menu") && e.is(":visible") && !$("body").hasClass("sidebar-collapse")) e.slideUp(c, function () {
                e.removeClass("menu-open")
            }), e.parent("li").removeClass("active"); else if (e.is(".treeview-menu") && !e.is(":visible")) {
                var f = d.parents("ul").first(), g = f.find("ul:visible").slideUp(c);
                g.removeClass("menu-open");
                var h = d.parent("li");
                e.slideDown(c, function () {
                    e.addClass("menu-open"), f.find("li.active").removeClass("active"), h.addClass("active"), b.layout.fix()
                })
            }
            e.is(".treeview-menu") && a.preventDefault()
        })
    }, $.AdminLTE.controlSidebar = {
        activate: function () {
            var a = this, b = $.AdminLTE.options.controlSidebarOptions, c = $(b.selector), d = $(b.toggleBtnSelector);
            d.on("click", function (d) {
                d.preventDefault(), c.hasClass("control-sidebar-open") || $("body").hasClass("control-sidebar-open") ? a.close(c, b.slide) : a.open(c, b.slide)
            });
            var e = $(".control-sidebar-bg");
            a._fix(e), $("body").hasClass("fixed") ? a._fixForFixed(c) : $(".content-wrapper, .right-side").height() < c.height() && a._fixForContent(c)
        }, open: function (a, b) {
            b ? a.addClass("control-sidebar-open") : $("body").addClass("control-sidebar-open")
        }, close: function (a, b) {
            b ? a.removeClass("control-sidebar-open") : $("body").removeClass("control-sidebar-open")
        }, _fix: function (a) {
            var b = this;
            if ($("body").hasClass("layout-boxed")) {
                if (a.css("position", "absolute"), a.height($(".wrapper").height()), b.hasBindedResize) return;
                $(window).resize(function () {
                    b._fix(a)
                }), b.hasBindedResize = !0
            } else a.css({position: "fixed", height: "auto"})
        }, _fixForFixed: function (a) {
            a.css({position: "fixed", "max-height": "100%", overflow: "auto", "padding-bottom": "50px"})
        }, _fixForContent: function (a) {
            $(".content-wrapper, .right-side").css("min-height", a.height())
        }
    }, $.AdminLTE.boxWidget = {
        selectors: $.AdminLTE.options.boxWidgetOptions.boxWidgetSelectors,
        icons: $.AdminLTE.options.boxWidgetOptions.boxWidgetIcons,
        animationSpeed: $.AdminLTE.options.animationSpeed,
        activate: function (a) {
            var b = this;
            a || (a = document), $(a).on("click", b.selectors.collapse, function (a) {
                a.preventDefault(), b.collapse($(this))
            }), $(a).on("click", b.selectors.remove, function (a) {
                a.preventDefault(), b.remove($(this))
            })
        },
        collapse: function (a) {
            var b = this, c = a.parents(".box").first(),
                d = c.find("> .box-body, > .box-footer, > form  >.box-body, > form > .box-footer");
            c.hasClass("collapsed-box") ? (a.children(":first").removeClass(b.icons.open).addClass(b.icons.collapse), d.slideDown(b.animationSpeed, function () {
                c.removeClass("collapsed-box")
            })) : (a.children(":first").removeClass(b.icons.collapse).addClass(b.icons.open), d.slideUp(b.animationSpeed, function () {
                c.addClass("collapsed-box")
            }))
        },
        remove: function (a) {
            var b = a.parents(".box").first();
            b.slideUp(this.animationSpeed)
        }
    }
}

if ("undefined" == typeof jQuery) throw new Error("AdminLTE requires jQuery");
$.AdminLTE = {}, $.AdminLTE.options = {
    navbarMenuSlimscroll: !0,
    navbarMenuSlimscrollWidth: "3px",
    navbarMenuHeight: "200px",
    animationSpeed: 500,
    sidebarToggleSelector: "[data-toggle='offcanvas']",
    sidebarPushMenu: !0,
    sidebarSlimScroll: !0,
    sidebarExpandOnHover: !1,
    enableBoxRefresh: !0,
    enableBSToppltip: !0,
    BSTooltipSelector: "[data-toggle='tooltip']",
    enableFastclick: !1,
    enableControlTreeView: !0,
    enableControlSidebar: !0,
    controlSidebarOptions: {
        toggleBtnSelector: "[data-toggle='control-sidebar']",
        selector: ".control-sidebar",
        slide: !0
    },
    enableBoxWidget: !0,
    boxWidgetOptions: {
        boxWidgetIcons: {collapse: "fa-minus", open: "fa-plus", remove: "fa-times"},
        boxWidgetSelectors: {remove: '[data-widget="remove"]', collapse: '[data-widget="collapse"]'}
    },
    directChat: {enable: !0, contactToggleSelector: '[data-widget="chat-pane-toggle"]'},
    colors: {
        lightBlue: "#3c8dbc",
        red: "#f56954",
        green: "#00a65a",
        aqua: "#00c0ef",
        yellow: "#f39c12",
        blue: "#0073b7",
        navy: "#001F3F",
        teal: "#39CCCC",
        olive: "#3D9970",
        lime: "#01FF70",
        orange: "#FF851B",
        fuchsia: "#F012BE",
        purple: "#8E24AA",
        maroon: "#D81B60",
        black: "#222222",
        gray: "#d2d6de"
    },
    screenSizes: {xs: 480, sm: 768, md: 992, lg: 1200}
}, $(function () {
    "use strict";
    $("body").removeClass("hold-transition"), "undefined" != typeof AdminLTEOptions && $.extend(!0, $.AdminLTE.options, AdminLTEOptions);
    var a = $.AdminLTE.options;
    _init(), $.AdminLTE.layout.activate(), a.enableControlTreeView && $.AdminLTE.tree(".sidebar"), a.enableControlSidebar && $.AdminLTE.controlSidebar.activate(), a.navbarMenuSlimscroll && "undefined" != typeof $.fn.slimscroll && $(".navbar .menu").slimscroll({
        height: a.navbarMenuHeight,
        alwaysVisible: !1,
        size: a.navbarMenuSlimscrollWidth
    }).css("width", "100%"), a.sidebarPushMenu && $.AdminLTE.pushMenu.activate(a.sidebarToggleSelector), a.enableBSToppltip && $("body").tooltip({
        selector: a.BSTooltipSelector,
        container: "body"
    }), a.enableBoxWidget && $.AdminLTE.boxWidget.activate(), a.enableFastclick && "undefined" != typeof FastClick && FastClick.attach(document.body), a.directChat.enable && $(document).on("click", a.directChat.contactToggleSelector, function () {
        var a = $(this).parents(".direct-chat").first();
        a.toggleClass("direct-chat-contacts-open")
    }), $('.btn-group[data-toggle="btn-toggle"]').each(function () {
        var a = $(this);
        $(this).find(".btn").on("click", function (b) {
            a.find(".btn.active").removeClass("active"), $(this).addClass("active"), b.preventDefault()
        })
    })
}), function (a) {
    "use strict";
    a.fn.boxRefresh = function (b) {
        function c(a) {
            a.append(f), e.onLoadStart.call(a)
        }

        function d(a) {
            a.find(f).remove(), e.onLoadDone.call(a)
        }

        var e = a.extend({
            trigger: ".refresh-btn", source: "", onLoadStart: function (a) {
                return a
            }, onLoadDone: function (a) {
                return a
            }
        }, b), f = a('<div class="overlay"><div class="fa fa-refresh fa-spin"></div></div>');
        return this.each(function () {
            if ("" === e.source) return void(window.console && window.console.log("Please specify a source first - boxRefresh()"));
            var b = a(this), f = b.find(e.trigger).first();
            f.on("click", function (a) {
                a.preventDefault(), c(b), b.find(".box-body").load(e.source, function () {
                    d(b)
                })
            })
        })
    }
}(jQuery), function (a) {
    "use strict";
    a.fn.activateBox = function () {
        a.AdminLTE.boxWidget.activate(this)
    }, a.fn.toggleBox = function () {
        var b = a(a.AdminLTE.boxWidget.selectors.collapse, this);
        a.AdminLTE.boxWidget.collapse(b)
    }, a.fn.removeBox = function () {
        var b = a(a.AdminLTE.boxWidget.selectors.remove, this);
        a.AdminLTE.boxWidget.remove(b)
    }
}(jQuery), function (a) {
    "use strict";
    a.fn.todolist = function (b) {
        var c = a.extend({
            onCheck: function (a) {
                return a
            }, onUncheck: function (a) {
                return a
            }
        }, b);
        return this.each(function () {
            "undefined" != typeof a.fn.iCheck ? (a("input", this).on("ifChecked", function () {
                var b = a(this).parents("li").first();
                b.toggleClass("done"), c.onCheck.call(b)
            }), a("input", this).on("ifUnchecked", function () {
                var b = a(this).parents("li").first();
                b.toggleClass("done"), c.onUncheck.call(b)
            })) : a("input", this).on("change", function () {
                var b = a(this).parents("li").first();
                b.toggleClass("done"), a("input", b).is(":checked") ? c.onCheck.call(b) : c.onUncheck.call(b)
            })
        })
    }
}(jQuery);