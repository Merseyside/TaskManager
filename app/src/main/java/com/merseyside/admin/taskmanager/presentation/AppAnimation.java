package com.merseyside.admin.taskmanager.presentation;

/**
 * Created by ivan_ on 07.11.2017.
 */

public class AppAnimation {
    public enum ImageViewAnimation{
        FADING(android.R.anim.fade_in);

        private int value;

        ImageViewAnimation(int value) {this.value = value;}

        public int getValue() {return value;}

        /*public static SortingOrder getOrder(String value) {
            SortingOrder[] values = SortingOrder.values();
            for (SortingOrder order : values) {
                if (order.getValue().equals(value)) return order;
            }
            return null;
        }*/
    }
}
