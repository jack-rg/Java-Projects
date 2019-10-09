/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 */
import java.awt.Color;
public class Cell
{
   private String symbol;
   private Color color;

   public Cell(String symbol, Color x){
     this.symbol = symbol;
     color = x;
   }

   
  public Color getColor()
   {
    return color;
   }

   public void setColor(Color color)
   {
      this.color = color;
   }
   
   public String getSymbol(){
     return symbol;
   }
   
   public void setSymbol(String s){
     symbol = s;
   }
   public String getText() {
        return symbol;
   } 
   
   public String setText(String s) {
        return symbol=s;
   } 
   

   

}
