type Fn = (...params: number[]) => number

function memoize(fn: Fn): Fn {
    const cache = new Map<string, number>();

    return function(...args) {
        const key = JSON.stringify(args);

        if(cache.has(key)) return cache.get(key);

        const res = fn(...args);
        cache.set(key, res);
        return res;
    }
}


/** 
 * let callCount = 0;
 * const memoizedFn = memoize(function (a, b) {
 *	 callCount += 1;
 *   return a + b;
 * })
 * memoizedFn(2, 3) // 5
 * memoizedFn(2, 3) // 5
 * console.log(callCount) // 1 
 */