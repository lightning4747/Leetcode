class Solution:
    def sumOfGoodNumbers(self, nums: List[int], k: int) -> int:
        n = len(nums)
        count = 0
        for i in range(0,n):
            good = True
            if i-k >=0:
                if nums[i] <= nums[i-k] :
                    good = False
            if i+k <n:
                if nums[i] <= nums[i+k] :
                    good = False

            if(good):
                count+=nums[i]   
        return count                     