package com.example.ruchi.azurecontact;

/**
 * Created by Ruchi on 2015-05-16.
 */
public class Contact {

    @com.google.gson.annotations.SerializedName("id")
    private String cId;

    @com.google.gson.annotations.SerializedName("name")
    private String cName;

    @com.google.gson.annotations.SerializedName("email")
    private String cEmail;

    @com.google.gson.annotations.SerializedName("phone")
    private String cPhone;

    /**
     * Contact constructor
     */
    public Contact() {

    }

    /**
     * Initializes a new Contact
     *
     * @param name
     *            The contact name
     * @param email
     *            The contact email
     * @param phone
     *            The contact phone
     * @param id
     *            The item id
     */
    public Contact(String name, String email, String phone, String id) {
        this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
        this.setId(id);
    }

    /**
     * Returns the contact name
     */
    public String getName() {
        return cName;
    }

    /**
     * Sets the contact name
     *
     * @param name
     *            name to set
     */
    public final void setName(String name) {
        cName = name;
    }

    /**
     * Returns the contact email
     */
    public String getEmail() {
        return cEmail;
    }

    /**
     * Sets the contact email
     *
     * @param email
     *            email to set
     */
    public final void setEmail(String email) {
        cEmail = email;
    }

    /**
     * Returns the contact phone
     */
    public String getPhone() {
        return cPhone;
    }

    /**
     * Sets the contact phone
     *
     * @param phone
     *             phone to set
     */
    public final void setPhone(String phone) {
        cPhone = phone;
    }

    /**
     * Returns the item id
     */
    public String getId() {
        return cId;
    }

    /**
     * Sets the item id
     *
     * @param id
     *            id to set
     */
    public final void setId(String id) {
        cId = id;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Contact && ((Contact) o).cId == cId;
    }
}
