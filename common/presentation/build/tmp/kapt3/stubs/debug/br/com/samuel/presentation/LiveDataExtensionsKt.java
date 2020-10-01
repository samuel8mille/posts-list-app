package br.com.samuel.presentation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a(\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a\u001c\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003\u001a)\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\u0006\u0010\t\u001a\u0002H\u0002\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"setError", "", "T", "Landroidx/lifecycle/MutableLiveData;", "Lbr/com/samuel/presentation/Resource;", "message", "", "setLoading", "setSuccess", "data", "(Landroidx/lifecycle/MutableLiveData;Ljava/lang/Object;)V", "presentation_debug"})
public final class LiveDataExtensionsKt {
    
    public static final <T extends java.lang.Object>void setSuccess(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<br.com.samuel.presentation.Resource<T>> $this$setSuccess, T data) {
    }
    
    public static final <T extends java.lang.Object>void setLoading(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<br.com.samuel.presentation.Resource<T>> $this$setLoading) {
    }
    
    public static final <T extends java.lang.Object>void setError(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<br.com.samuel.presentation.Resource<T>> $this$setError, @org.jetbrains.annotations.Nullable()
    java.lang.String message) {
    }
}