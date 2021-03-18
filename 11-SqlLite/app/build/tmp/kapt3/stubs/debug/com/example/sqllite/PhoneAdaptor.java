package com.example.sqllite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\u0014\u0010\u0017\u001a\u00020\u00102\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u000bR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/sqllite/PhoneAdaptor;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/sqllite/PhoneAdaptor$PhoneViewHolder;", "context", "Landroid/content/Context;", "phoneViewModel", "Lcom/example/sqllite/PhoneViewModel;", "(Landroid/content/Context;Lcom/example/sqllite/PhoneViewModel;)V", "getContext", "()Landroid/content/Context;", "listOfPhones", "", "Lcom/example/sqllite/Phone;", "getItemCount", "", "onBindViewHolder", "", "holderPhone", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setPhones", "phones", "PhoneViewHolder", "app_debug"})
public final class PhoneAdaptor extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.sqllite.PhoneAdaptor.PhoneViewHolder> {
    private java.util.List<com.example.sqllite.Phone> listOfPhones;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final com.example.sqllite.PhoneViewModel phoneViewModel = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.sqllite.PhoneAdaptor.PhoneViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.sqllite.PhoneAdaptor.PhoneViewHolder holderPhone, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void setPhones(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.sqllite.Phone> phones) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public PhoneAdaptor(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.example.sqllite.PhoneViewModel phoneViewModel) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/sqllite/PhoneAdaptor$PhoneViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/sqllite/databinding/ListElementBinding;", "(Lcom/example/sqllite/databinding/ListElementBinding;)V", "getBinding", "()Lcom/example/sqllite/databinding/ListElementBinding;", "app_debug"})
    public static final class PhoneViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.sqllite.databinding.ListElementBinding binding = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.sqllite.databinding.ListElementBinding getBinding() {
            return null;
        }
        
        public PhoneViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.sqllite.databinding.ListElementBinding binding) {
            super(null);
        }
    }
}