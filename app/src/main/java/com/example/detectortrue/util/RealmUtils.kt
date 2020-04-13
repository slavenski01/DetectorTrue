package com.example.detectortrue.util

import io.realm.Realm
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend inline fun Realm.inTransactionAsync(crossinline receiver: Realm.() -> Unit) {
    return suspendCoroutine { continuation ->
        this.executeTransactionAsync({ realm ->
            receiver(realm)
        }, {
            continuation.resume(Unit)
        }, { fail ->
            continuation.resumeWithException(fail)
        })
    }
}

suspend inline fun Realm.inClosingTransactionAsync(crossinline receiver: Realm.() -> Unit) {
    return suspendCoroutine { continuation ->
        this.executeTransactionAsync({ realm ->
            receiver(realm)
        }, {
            continuation.resume(Unit)
            close()
        }, { fail ->
            continuation.resumeWithException(fail)
            close()
        })
    }
}