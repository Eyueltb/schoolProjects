def two_sum(self, k):
    for i in range(len(self)):
        for j in range(len(self)):
            if i != j and self[i] + self[j] == k:
                return True
    return False


def two_sum_set(lst, k):
    seen = set()
    for num in lst:
        if k - num in seen:
            return True
        seen.add(num)
    return False


def two_sum_two_pointers(lst, k):
    lst.sort()
    left = 0
    right = len(lst)-1
    while left < right:
        sums = lst[left] + lst[right]
        if sums == k:
            return True
        elif sums < k:
            left += 1
        else:
            right -= 1

    return False


print(two_sum([10, 15, 3, 7], 17))

print(two_sum_set([10, 15, 3, 7], 17))

print(two_sum_two_pointers([10, 15, 3, 7], 17))
