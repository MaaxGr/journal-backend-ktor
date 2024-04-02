package net.grossmax.journal.ktor.utils

import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent.getKoin
import org.koin.mp.KoinPlatformTools

inline fun <reified T : Any> get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    return getKoin().get(qualifier, parameters)
}

/**
 * Lazy inject instance from Koin
 * @param qualifier
 * @param mode - LazyThreadSafetyMode
 * @param parameters
 */
inline fun <reified T : Any> inject(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = KoinPlatformTools.defaultLazyMode(),
    noinline parameters: ParametersDefinition? = null,
): Lazy<T> = lazy(mode) { get<T>(qualifier, parameters) }
