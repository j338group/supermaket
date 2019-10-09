package cn.smbms.pojo;

import java.util.Date;

public class Provider {
    private Long id;

    private String proCode;

    private String proName;

    private String proDesc;

    private String proContact;

    private String proPhone;

    private String proAddress;

    private String proFax;

    private Long createdBy;

    private Date creationDate;

    private Date modifyDate;

    private Long modifyBy;

    public Provider(Long id, String proCode, String proName, String proDesc, String proContact, String proPhone, String proAddress, String proFax, Long createdBy, Date creationDate, Date modifyDate, Long modifyBy) {
        this.id = id;
        this.proCode = proCode;
        this.proName = proName;
        this.proDesc = proDesc;
        this.proContact = proContact;
        this.proPhone = proPhone;
        this.proAddress = proAddress;
        this.proFax = proFax;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.modifyBy = modifyBy;
    }
}