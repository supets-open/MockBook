package com.supets.pet.module.paste;


import android.graphics.PointF;

public class MathUtils {

    // 判断点 q 是否在多边形内，其中多边形是任意的凸或凹多边形， Polygon中存放多边形的逆时针顶点序列
    public static boolean pinplg(int vcount, PointF polygon[], PointF q) {
        //System.out.println("polygon:" + Arrays.toString(polygon));
        int c = 0, i, n;
        Llineseg l1 = new Llineseg();
        Llineseg l2 = new Llineseg();
        l1.a = new PointF(q.x, q.y);
        l1.b = new PointF(q.x, q.y);
        l1.b.x = (float) Math.pow(10, 10);
        n = vcount;
        for (i = 0; i < vcount; i++) {
            l2.a = polygon[i];
            l2.b = polygon[(i + 1) % n];
            if ((lsinterls_A(l1, l2))
                    || ((ponls(l1, polygon[(i + 1) % n])) && ((!ponls(l1, polygon[(i + 2) % n]))
                            && (xmulti(polygon[i], polygon[(i + 1) % n], l1.a) * xmulti(polygon[(i + 1) % n], polygon[(i + 2) % n], l1.a) > 0) || (ponls(l1, polygon[(i + 2) % n]))
                            && (xmulti(polygon[i], polygon[(i + 2) % n], l1.a) * xmulti(polygon[(i + 2) % n], polygon[(i + 3) % n], l1.a) > 0)))) {
                c++;
            }
        }
//        System.out.println("result:" + (c % 2 != 0));
        return (c % 2 != 0);
    }

    public static class Llineseg {
        public PointF a = new PointF();
        public PointF b = new PointF();
        
        @Override
        public String toString() {
            return String.format("a=%s, b=%s", a.toString(), b.toString());
        }
    }

    // 判断点 p 是否在线段 l 上
    private static boolean ponls(Llineseg l, PointF p) {
        return ((xmulti(l.b, p, l.a) == 0) && (((p.x - l.a.x) * (p.x - l.b.x) < 0) || ((p.y - l.a.y) * (p.y - l.b.y) < 0)));
    }

    // (P1-P0)*(P2-P0)的叉积
    public static double xmulti(PointF p1, PointF p2, PointF p0) {
        return (1.0 * (p1.x - p0.x) * (p2.y - p0.y)) - (1.0 * (p2.x - p0.x) * (p1.y - p0.y));
    }

    // 线段相交判断函数
    // 当且仅当u,v相交并且交点不是u,v的端点时函数为true;
    private static boolean lsinterls_A(Llineseg u, Llineseg v) {
        return lsinterls(u, v) && !u.a.equals(v.a) && (!u.a.equals(v.b)) && (!u.b.equals(v.a)) && (!u.b.equals(v.b));
    }

    // 确定两条线段是否相交
    public static boolean lsinterls(Llineseg u, Llineseg v) {
        boolean r = ((Math.max(u.a.x, u.b.x) >= Math.min(v.a.x, v.b.x)) 
                && (Math.max(v.a.x, v.b.x) >= Math.min(u.a.x, u.b.x)) 
                && (Math.max(u.a.y, u.b.y) >= Math.min(v.a.y, v.b.y)) 
                && (Math.max(v.a.y, v.b.y) >= Math.min(u.a.y, u.b.y))
                && (xmulti(v.a, u.b, u.a) * xmulti(u.b, v.b, u.a) >= 0) 
                && (xmulti(u.a, v.b, v.a) * xmulti(v.b, u.b, v.a) >= 0)); 

        return r;
    }

    public static double getDistanceOfTwoPoints(PointF p1, PointF p2) {
        return getDistanceOfTwoPoints(p1.x, p1.y, p2.x, p2.y);
    }

    public static double getDistanceOfTwoPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static double radianToAngle(double randian) {
        return 180.0 * randian / Math.PI;
    }

    public static double calcRotateDirection(PointF o, PointF a, PointF b) {
        double cross = (1.0 * (a.x - o.x) * (b.y - o.y)) - (1.0 * (b.x - o.x) * (a.y - o.y));
        return cross >= 0 ? 1.0 : -1.0;
    }

    public static double calcDegrees(PointF center, PointF start, PointF end) {
        double direction = MathUtils.calcRotateDirection(center, start, end);

        double a = MathUtils.getDistanceOfTwoPoints(end, center);
        double b = MathUtils.getDistanceOfTwoPoints(start, center);
        double c = MathUtils.getDistanceOfTwoPoints(start, end);
        double cosC = (a * a + b * b - c * c) / (2 * a * b); // 余弦定理
        double angle = MathUtils.radianToAngle(Math.acos(cosC));
        angle = Math.abs(angle) * direction;
        return Double.isNaN(angle) ? 0 : angle;
    }
    
}
