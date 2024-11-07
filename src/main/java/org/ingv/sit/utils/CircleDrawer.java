/**
 *
 *
 * @author Andrea Bono
 * I.N.G.V. Istituto Nazionale di Geofisica e Vulcanologia
 * O.N.T. Osservatorio Nazionale Terremoti
 * Via di Vigna Murata 605
 * 00143 Rome
 * ITALY
 *
 * andrea.bono@ingv.it
 * +39 0651860290
 * 
 */

package org.ingv.sit.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;


public class CircleDrawer implements org.jfree.chart.ui.Drawable{
     /** The outline paint. */
     private Paint outlinePaint;

     /** The outline stroke. */
    private Stroke outlineStroke;

  /** The fill paint. */
   private Paint fillPaint;

/**
69      * Creates a new instance.
70      *
71      * @param outlinePaint the outline paint.
72      * @param outlineStroke the outline stroke.
73      * @param fillPaint the fill paint.
74      */
     public CircleDrawer(Paint  outlinePaint, Stroke  outlineStroke, Paint fillPaint) {
         this.outlinePaint = outlinePaint;
         this.outlineStroke = outlineStroke;
         this.fillPaint = fillPaint;
     }

/**
82      * Draws the circle.
83      *
84      * @param g2 the graphics device.
85      * @param area the area in which to draw.
86      */
     public void draw(Graphics2D   g2, Rectangle2D   area) {
         Ellipse2D   ellipse = new Ellipse2D.Double  (area.getX(), area.getY(),
                                                  area.getWidth(), area.getHeight());
         if (this.fillPaint != null) {
             g2.setPaint(this.fillPaint);
             g2.fill(ellipse);
         }
         if (this.outlinePaint != null && this.outlineStroke != null) {
             g2.setPaint(this.outlinePaint);
             g2.setStroke(this.outlineStroke);
             g2.draw(ellipse);
         }

         g2.setPaint(Color.black);
         g2.setStroke(new BasicStroke  (1.0f));
         Line2D   line1 = new Line2D.Double  (area.getCenterX(), area.getMinY(),
                                          area.getCenterX(), area.getMaxY());
         Line2D   line2 = new Line2D.Double  (area.getMinX(), area.getCenterY(),
                                          area.getMaxX(), area.getCenterY());
         g2.draw(line1);
         g2.draw(line2);
     }




}
