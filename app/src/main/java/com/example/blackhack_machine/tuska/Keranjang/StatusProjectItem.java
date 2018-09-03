package com.example.blackhack_machine.tuska.Keranjang;

public class StatusProjectItem {
    String orderNumText, lastUpdateText, statusProjectText, picName, linkAttch, projectImage ;

    public StatusProjectItem(String orderNumText, String lastUpdateText, String statusProjectText, String picName, String linkAttch, String projectImage) {
        this.orderNumText = orderNumText;
        this.lastUpdateText = lastUpdateText;
        this.statusProjectText = statusProjectText;
        this.picName = picName;
        this.linkAttch = linkAttch;
        this.projectImage = projectImage;
    }

    public String getOrderNumText() {
        return orderNumText;
    }

    public String getLastUpdateText() {
        return lastUpdateText;
    }

    public String getstatusProjectText() {
        return statusProjectText;
    }

    public String getpicName() {
        return picName;
    }

    public String getlinkAttch() {
        return linkAttch;
    }
    public String getprojectImage() {
        return projectImage;
    }

}
